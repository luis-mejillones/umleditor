package umleditor;

public class MainEditor implements Application {

    @Override
    public void run() {
        Link link = new LinkRule();
        link.addRule(UmlObjectType.IMPLEMENTATION, UmlObjectType.INTERFACE);
        link.addRule(UmlObjectType.INTERFACE, UmlObjectType.IMPLEMENTATION);
        link.addRule(UmlObjectType.IMPLEMENTATION, UmlObjectType.CLASS);
        link.addRule(UmlObjectType.CLASS, UmlObjectType.IMPLEMENTATION);
        
        ShapeContainer interfaceShape = new InterfaceShape(link);
        ShapeContainer classShape = new ClassShape(link);
        ShapeContainer implementsConnector = new ImplementsConnector(link);
        
        implementsConnector.link(interfaceShape);
        implementsConnector.link(classShape);
        
        implementsConnector.display();
        interfaceShape.display();
        classShape.display();
        
    }
    
}
