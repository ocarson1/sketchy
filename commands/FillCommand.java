package sketchy.commands;

import javafx.scene.paint.Color;
import sketchy.shapes.SketchyShape;

public class FillCommand implements Command {
    private Color prevColor;
    private Color currColor;
    private SketchyShape myShape;

    public FillCommand(SketchyShape shape, Color curr){
        this.prevColor = shape.getColor();
        this.currColor = curr;
        this.myShape = shape;
    }

    @Override
    public void undo(){
        this.myShape.fill(this.prevColor);
    }

    @Override
    public void redo(){
        this.myShape.fill(this.currColor);
    }
}



