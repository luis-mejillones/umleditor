package umleditor;

import java.util.ArrayList;
import java.util.List;

public class ImplementsConnector extends Connector {
    private final static int DEFAULT_LINE_X1 = 15;
    private final static int DEFAULT_LINE_Y1 = 100;
    private final static int DEFAULT_LINE_X2 = 90;
    private final static int DEFAULT_LINE_Y2 = 100;

    UmlObjectType type;
    List<ShapeContainer> linkedObjects;
    Link linkRule;
    
    public ImplementsConnector(Link linkRule) {
        super(LineType.DASHED, HeadType.WHITE_TRIANGLE, new Line(
                DEFAULT_LINE_X1,
                DEFAULT_LINE_Y1,
                DEFAULT_LINE_X2,
                DEFAULT_LINE_Y2
        ));
        this.type = UmlObjectType.IMPLEMENTATION;
        this.linkedObjects = new ArrayList<>();
        this.linkRule = linkRule;
    }

    @Override
    public boolean link(ShapeContainer shape) {
        if (this.linkRule.canLink(this.type, shape.getType())) {
            shape.link(this);
            return this.linkedObjects.add(shape);
        }
        
        return false;
    }

    @Override
    public boolean unlink(ShapeContainer shape) {
        return this.linkedObjects.remove(shape);
    }

    @Override
    public UmlObjectType getType() {
        return this.type;
    }
    
    

    @Override
    public void display() {
        System.out.println("ImplementsConnector:" + super.headType + "-" + super.linetype);
        System.out.println("  linked:" + this.linkedObjects);
    } 
}
