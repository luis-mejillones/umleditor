package umleditor;

import java.util.ArrayList;
import java.util.List;

public class AgregationConnector extends Connector {
    UmlObjectType type;
    List<ShapeContainer> linkedObjects;
    Link linkRule;
    
    public AgregationConnector(Link linkRule) {
        super(LineType.SOLID, HeadType.WHITE_DIAMOND);
        this.type = UmlObjectType.AGREGATION;
        this.linkedObjects = new ArrayList<>();
        this.linkRule = linkRule;
    }

    @Override
    public boolean link(ShapeContainer shape) {
        if (this.linkRule.canLink(this.type, shape.getType())) {
            shape.link(this);
            return this.linkedObjects.add(shape);
        }
        System.out.println("Can't link " + this.type + " to " + shape.getType());
        
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
        System.out.println(
                "ImplementsConnector:" + super.headType + "-" + super.linetype +
                        " linked to:" + this.linkedObjects
        );
    } 
}
