package umleditor;

import umleditor.view.RectangleView;

public class Rectangle {
    private final static int DEFAULT_HEIGHT = 50;
    private final static int DEFAULT_WIDTH = 100;
    private final static int DEFAULT_X = 10;
    private final static int DEFAULT_Y = 10;

    RectangleView view;
    
    
    public Rectangle() {
        view = new RectangleView(DEFAULT_X, DEFAULT_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
    }
    
    public void draw() {
        this.view.draw();
    }
    
    public void setX(int x) {
        this.view.setX(x);
    }

    public void setY(int y) {
        this.view.setY(y);
    }

    public void setPosition(int x, int y) {
        this.view.setPosition(x, y);
    }
    
    public int getHeight() {
        return this.view.getHeight();
    }

    public void setHeight(int height) {
        this.view.setHeight(height);
    }

    public int getWidth() {
        return this.view.getWidth();
    }

    public void setWidth(int width) {
        this.view.setWidth(width);
    }
    
    void display() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return this.view.toString();
    }

}
