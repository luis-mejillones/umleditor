package umleditor;

public class TextContent {
    String label;
    String body;
    Rectangle rectangle;
    public TextContent(String label, String body) {
        this.label = label;
        this.body = body;
        this.rectangle = new Rectangle();
    }
    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

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
        return "{" + label + ", " + body + "} " + this.rectangle;
    }
    
}
