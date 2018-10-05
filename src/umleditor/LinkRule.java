package umleditor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkRule implements Link {
    private final Map<UmlObjectType, List<UmlObjectType>> rules;

    public LinkRule() {
        this.rules = new HashMap<>();
    }
 

    @Override
    public void addRule(UmlObjectType source, UmlObjectType destination) {
        if (!this.rules.containsKey(source)) {
            this.rules.put(source, new ArrayList<>());
        }
        
        this.rules.get(source).add(destination);
    }

    @Override
    public boolean canLink(UmlObjectType source, UmlObjectType destination) {
        if (this.rules.containsKey(source)) {
            return this.rules.get(source).contains(destination);
        }
        
        return false;
    }
    
    
}
