package umleditor;

import java.util.ArrayList;
import java.util.List;

public class ClassShape extends GeneralUmlObject {
    UmlObjectType type;
    List<ShapeContainer> linkedObjects;
    Link linkRule;
    
    public ClassShape(Link linkRule) {
        super(new ShapeContent("class name", "body class"));
        this.type = UmlObjectType.CLASS;
        this.linkedObjects = new ArrayList<>();
        this.linkRule = linkRule;
    }
    
    @Override
    public boolean link(ShapeContainer shape) {
        if (this.linkRule.canLink(this.type, shape.getType())) {
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
        System.out.println("Class: " + super.shapeContent);
        System.out.println("  linked:" + this.linkedObjects);
    }
    
}
