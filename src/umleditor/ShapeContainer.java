package umleditor;

public interface ShapeContainer extends DisplayAble, GiveType, DrawAble, PositionAble {
    boolean link(ShapeContainer shape);
    boolean unlink(ShapeContainer shape);
}
