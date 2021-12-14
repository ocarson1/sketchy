package sketchy.commands;

import javafx.geometry.Point2D;
import sketchy.shapes.SketchyShape;

public class ResizeCommand implements Command{
    private SketchyShape myShape;
    private Point2D prevSize;
    private Point2D newSize;

    public ResizeCommand(SketchyShape shape, Point2D prev,Point2D curr){
        this.myShape = shape;
        this.prevSize = prev;
        this.newSize = curr;
    }

    @Override
    public void undo(){
        this.myShape.resize(this.prevSize);
    }

    @Override
    public void redo(){
        this.myShape.resize(this.newSize);
    }
}
