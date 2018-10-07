package umleditor.view;

import java.awt.Graphics2D;

public interface Shape {

    int getX();

    int getY();
    
    void setX(int x);
    void setY(int y);

    int getWidth();

    int getHeight();

    void paintShape(Graphics2D g2);

    void draw();
    
    boolean contains(int x, int y);
}
