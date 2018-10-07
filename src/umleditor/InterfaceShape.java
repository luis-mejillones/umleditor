package umleditor;

import java.util.ArrayList;
import java.util.List;

public class InterfaceShape extends GeneralUmlObject {
    UmlObjectType type;
    List<ShapeContainer> linkedObjects;
    Link linkRule;
    
    public InterfaceShape(Link linkRule) {
        super(new TextContent("<<interface>>", "Name"));
        this.type = UmlObjectType.INTERFACE;
        this.linkedObjects = new ArrayList<>();
        this.linkRule = linkRule;
    }
    
    @Override
    public boolean link(ShapeContainer shape) {
        if (this.linkRule.canLink(this.type, shape.getType())) {
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
        System.out.println("Interface: " + super.textContent + " linked to:" + this.linkedObjects);
    }
    
}
