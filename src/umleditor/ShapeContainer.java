package umleditor;

public interface ShapeContainer extends DisplayAble, GiveType {
    boolean link(ShapeContainer shape);
    boolean unlink(ShapeContainer shape);
}
