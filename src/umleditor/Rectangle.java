package umleditor;

public class Rectangle {
    private final static int DEFAULT_LENGTH = 100;
    private final static int DEFAULT_WIDTH = 50;
    int length;
    int width;

    public Rectangle() {
        this.length = DEFAULT_LENGTH;
        this.width = DEFAULT_WIDTH;
    }
    
    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    
    void display() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Rectangle {" + length + ", " + width + "}";
    }

}
