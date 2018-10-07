package umleditor;

import umleditor.view.LineView;

public abstract class Connector implements ShapeContainer {
    LineType linetype;
    HeadType headType;
    LineView lineView;

    public Connector(LineType linetype, HeadType headType, Line line) {
        this.linetype = linetype;
        this.headType = headType;
        this.lineView = new LineView(line.getX1(),line.getY1(), line.getX2(), line.getY2());
        this.lineView.setType(linetype);
    }
    
    @Override
    public void draw() {
        lineView.draw();
    }
    
    @Override
    public void setX(int x) {
        // ToDo add drawable object and call it
    }

    @Override
    public void setY(int y) {
        // ToDo add drawable object and call it
    }

    @Override
    public void setPosition(int x, int y) {
        // ToDo add drawable object and call it
    }
}
