package umleditor.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class RectangleView implements Shape {

    private Color color = Color.BLACK;
    private boolean filled = false;
    private int x;
    private int y;
    private int width;
    private int height;

    public RectangleView() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
    }

    public RectangleView(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getX() {
        return (int) Math.round(x);
    }

    @Override
    public int getY() {
        return (int) Math.round(y);
    }

    @Override
    public int getWidth() {
        return (int) Math.round(width);
    }

    @Override
    public int getHeight() {
        return (int) Math.round(height);
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
    
    public void translate(double dx, double dy) {
        x += dx;
        y += dy;
        Canvas.getInstance().repaint();
    }

    public void grow(double dw, double dh) {
        width += 2 * dw;
        height += 2 * dh;
        x -= dw;
        y -= dh;
        Canvas.getInstance().repaint();
    }

    public void setColor(Color newColor) {
        color = newColor;
        Canvas.getInstance().repaint();
    }

    @Override
    public void draw() {
        filled = false;
        Canvas.getInstance().show(this);
    }

    public void fill() {
        filled = true;
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
        return "Rectangle[x=" + getX() + ",y=" + getY() + ",width=" + getWidth() + ",height=" + getHeight() + "]";
    }

    @Override
    public void paintShape(Graphics2D g2) {
        Rectangle2D.Double rect = new Rectangle2D.Double(
                getX(),
                getY(),
                getWidth(),
                getHeight()
        );
        g2.setColor(new java.awt.Color((int) color.getRed(), (int) color.getGreen(), (int) color.getBlue()));
        if (filled) {
            g2.fill(rect);
        } else {
            g2.draw(rect);
        }
    }
}
