package umleditor;

public abstract class GeneralUmlObject implements ShapeContainer {
    ShapeContent shapeContent;

    public GeneralUmlObject(ShapeContent textContent) {
        this.shapeContent = textContent;
    }

    @Override
    public void draw() {
        this.shapeContent.draw();
    }

    @Override
    public void setX(int x) {
        this.shapeContent.setX(x);
    }

    @Override
    public void setY(int y) {
        this.shapeContent.setY(y);
    }

    @Override
    public void setPosition(int x, int y) {
        this.shapeContent.setPosition(x, y);
    }
    
    
}
