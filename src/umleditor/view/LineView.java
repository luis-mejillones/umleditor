package umleditor.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import umleditor.LineType;

public class LineView implements Shape {
    private Color color = Color.BLACK;
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private LineType lineType;

    public LineView(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public boolean contains(int x, int y) {
        return false;
    }

    @Override
    public int getX() {
        return (int) Math.round(Math.min(x1, x2));
    }

    @Override
    public int getY() {
        return (int) Math.round(Math.min(y1, y2));
    }

    @Override
    public void setX(int x) {
        this.x1 = x;
    }

    @Override
    public void setY(int y) {
        this.y1 = y;
    }
    
    @Override
    public int getWidth() {
        return (int) Math.round(Math.abs(x2 - x1));
    }

    @Override
    public int getHeight() {
        return (int) Math.round(Math.abs(y2 - y1));
    }

    public void setType(LineType lineType) {
        this.lineType = lineType;
    }
    
    public void translate(double dx, double dy) {
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
        Canvas.getInstance().repaint();
    }

    public void grow(double dw, double dh) {
        if (x1 <= x2) {
            x1 -= dw;
            x2 += dw;
        } else {
            x1 += dw;
            x2 -= dw;
        }
        if (y1 <= y2) {
            y1 -= dh;
            y2 += dh;
        } else {
            y1 += dh;
            y2 -= dh;
        }

        Canvas.getInstance().repaint();
    }

    public void setColor(Color newColor) {
        color = newColor;
        Canvas.getInstance().repaint();
    }

    public void draw() {
        Canvas.getInstance().show(this);
    }

    public String toString() {
        return "Line[x1=" + x1 + ",y1=" + y1 + ",x2=" + x2 + ",y2=" + y2 + "]";
    }

    public void paintShape(Graphics2D g2) {
        if (color != null) {
            g2.setColor(new java.awt.Color((int) color.getRed(), (int) color.getGreen(), (int) color.getBlue()));
            Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);
            
            Stroke stroke = new BasicStroke(1);
            if (this.lineType == LineType.DASHED) {
                float[] dashingPattern = {4f, 2f};
                stroke = new BasicStroke(1f, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER, 1.0f, dashingPattern, 0.0f);    
            }
            
            g2.setStroke(stroke);
            g2.draw(line);
        }
    }
}
