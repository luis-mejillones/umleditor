package umleditor;

public abstract class Connector implements ShapeContainer {
    LineType linetype;
    HeadType headType;

    public Connector(LineType linetype, HeadType headType) {
        this.linetype = linetype;
        this.headType = headType;
    }
}
