package umleditor;

public abstract class GeneralUmlObject implements ShapeContainer {
    TextContent textContent;

    public GeneralUmlObject(TextContent textContent) {
        this.textContent = textContent;
    }
    
    
}
