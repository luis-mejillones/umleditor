package umleditor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainEditor implements Application {

    @Override
    public void run() {
        Link link = new LinkRule();
        link.addRule(UmlObjectType.IMPLEMENTS, UmlObjectType.INTERFACE);
        link.addRule(UmlObjectType.INTERFACE, UmlObjectType.IMPLEMENTS);
        link.addRule(UmlObjectType.EXTENDS, UmlObjectType.INTERFACE);
        link.addRule(UmlObjectType.INTERFACE, UmlObjectType.EXTENDS);
        link.addRule(UmlObjectType.IMPLEMENTS, UmlObjectType.CLASS);
        link.addRule(UmlObjectType.CLASS, UmlObjectType.IMPLEMENTS);
        link.addRule(UmlObjectType.EXTENDS, UmlObjectType.CLASS);
        link.addRule(UmlObjectType.CLASS, UmlObjectType.EXTENDS);
        
        List<ShapeContainer> list = new ArrayList<>();        
        list.add(new ImplementsConnector(link));
        list.add(new ExtendsConnector(link));
        list.add(new CompositionConnector(link));
        list.add(new AgregationConnector(link));
        list.add(new UseConnector(link));

        ShapeContainer interfaceShape = new InterfaceShape(link);
        ShapeContainer classShape = new ClassShape(link);
        
        Random rand = new Random();
        int index;
        ShapeContainer currentShape;
        for (int i = 0; i < 5; ++i) {
            index = rand.nextInt(list.size());
            currentShape = list.get(index);
            currentShape.link(interfaceShape);
            currentShape.link(classShape);
            currentShape.display();
        }
    }
}
