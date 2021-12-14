package sketchy.commands;

import sketchy.shapes.SketchyShape;

import java.nio.channels.SelectionKey;

public class RotateCommand implements Command{
    private SketchyShape myShape;
    private double prevAngle;
    private double newAngle;

    public RotateCommand(SketchyShape shape, double prev, double curr){
        this.myShape = shape;
        this.prevAngle = prev;
        this.newAngle = curr;
    }

    @Override
    public void undo(){
        this.myShape.setRotate(this.prevAngle);
    }

    @Override
    public void redo(){
        this.myShape.setRotate(this.newAngle);
    }
}
