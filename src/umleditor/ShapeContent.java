package umleditor;

import umleditor.view.Shape;
import umleditor.view.TextView;

public class ShapeContent {
    private final static int DEFAULT_X = 12;
    private final static int DEFAULT_Y = 12;
    
    private Shape label;
    private Shape body;
    private Rectangle rectangle;
    
    public ShapeContent(String label, String body) {
        this.label = new TextView(DEFAULT_X, DEFAULT_Y, label);
        this.body = new TextView(DEFAULT_X, DEFAULT_Y + 15, body);
        this.rectangle = new Rectangle();
    }
    
    public void draw() {
        this.rectangle.draw();
        this.label.draw();
        this.body.draw();
    }
    
    public void setX(int x) {
        this.rectangle.setX(x);
    }

    public void setY(int y) {
        this.rectangle.setY(y);
    }

    public void setPosition(int x, int y) {
        this.rectangle.setPosition(x, y);
    }
    
//    public String getLabel() {
//        return label;
//    }
//
//    public void setLabel(String label) {
//        this.label = label;
//    }
//
//    public String getBody() {
//        return body;
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    
    void display() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
//        return "{" + label + ", " + body + "} " + this.rectangle;
        return "{" + body + "} " + this.rectangle;
    }
    
}
