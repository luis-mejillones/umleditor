package umleditor.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;

public class TextView implements Shape {
    private Color color = Color.BLACK;
    private final JLabel label = new JLabel();
    private int x;
    private int y;
    private int xGrow;
    private int yGrow;

    public TextView(int x, int y, String message) {
        this.x = x;
        this.y = y;
        label.setFont(new Font("Arial", Font.PLAIN, 11));
        label.setText(message);
    }

    @Override
    public int getX() {
        return x - xGrow;
    }

    @Override
    public int getY() {
        return y - yGrow;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public int getWidth() {
        return (int) Math.round(label.getPreferredSize().getWidth() + 2 * xGrow);
    }

    @Override
    public int getHeight() {
        return (int) Math.round(label.getPreferredSize().getHeight() + 2 * yGrow);
    }

    public void translate(int dx, int dy) {
        x += dx;
        y += dy;
        Canvas.getInstance().repaint();
    }

    public void grow(int dw, int dh) {
        xGrow += dw;
        yGrow += dh;
        Canvas.getInstance().repaint();
    }

    public void setColor(Color newColor) {
        color = newColor;
        Canvas.getInstance().repaint();
    }

    @Override
    public void draw() {
        Canvas.getInstance().show(this);
    }

    @Override
    public boolean contains(int x, int y) {
        Rectangle2D.Double rect = new Rectangle2D.Double(
                getX(),
                getY(),
                getWidth(),
                getHeight()
        );
        
        return rect.contains(x, y);
    }
    
    @Override
    public String toString() {
        return "Text[x=" + getX() + ",y=" + getY() + ",message=" + label.getText() + "]";
    }

    @Override
    public void paintShape(Graphics2D g2) {
        if (color != null) {
            label.setForeground(new java.awt.Color((int) color.getRed(), (int) color.getGreen(), (int) color.getBlue()));
            Dimension dim = label.getPreferredSize();
            if (dim.width > 0 && dim.height > 0) {
                label.setBounds(0, 0, dim.width, dim.height);
                g2.translate(getX(), getY());
                g2.scale(getWidth() / dim.width, getHeight() / dim.height);
                label.paint(g2);
            }
        }
    }
}
