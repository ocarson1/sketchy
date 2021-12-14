package sketchy.commands;

import javafx.geometry.Point2D;
import sketchy.shapes.SketchyShape;

public class MoveCommand implements Command{
    private SketchyShape myShape;
    private Point2D prevLoc;
    private Point2D newLoc;

    public MoveCommand(SketchyShape shape, Point2D prev, Point2D curr){
        this.myShape = shape;
        this.prevLoc = prev;
        this.newLoc = curr;
    }

    @Override
    public void undo(){
        this.myShape.translate(this.newLoc,this.prevLoc);
    }

    @Override
    public void redo(){
        this.myShape.translate(this.prevLoc, this.newLoc);
    }
}
