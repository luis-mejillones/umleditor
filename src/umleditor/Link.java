package umleditor;

public interface Link {
    void addRule(UmlObjectType source, UmlObjectType destination);
    boolean canLink(UmlObjectType source, UmlObjectType destination);
}
