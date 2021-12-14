package sketchy.commands;

import sketchy.shapes.SketchyShape;

import java.util.ArrayList;

public class DeleteCommand implements Command{
    private SketchyShape myShape;
    private int paneIndex;
    private int arrayIndex;
    private ArrayList<SketchyShape> myList;

    public DeleteCommand(SketchyShape shape, ArrayList<SketchyShape> shapeList) {
        this.myShape = shape;
        this.paneIndex = shape.getIndex();
        this.arrayIndex = shapeList.indexOf(shape);
        this.myList = shapeList;
    }

    @Override
    public void undo() {
        this.myShape.create(this.paneIndex);
        this.myList.add(this.arrayIndex,this.myShape);
    }

    @Override
    public void redo(){
        this.myShape.delete();
        this.myList.remove(this.myShape);
    }

}
