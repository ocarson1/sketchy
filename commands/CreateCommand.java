package sketchy.commands;

import javafx.geometry.Point2D;
import sketchy.shapes.SketchyShape;

import java.util.ArrayList;

public class CreateCommand implements Command {
    private SketchyShape myShape;
    private int paneIndex;
    private int arrayIndex;
    private ArrayList<SketchyShape> myList;

    public CreateCommand(SketchyShape shape, int index, ArrayList shapeList) {
        this.myShape = shape; // all of these private variables are being saved so that they are stored within the instance.
        this.paneIndex = index; //maybe edit to take in the entire arraylist as a parameter and establish the first on on the area list as the shape?
        this.arrayIndex = shapeList.size()-1;
        this.myList = shapeList;

    }

    public void undo() {
        this.myShape.delete();
        this.myList.remove(this.myShape);
    }

    public void redo(){
        this.myShape.create(this.paneIndex);
        this.myList.add(this.arrayIndex, this.myShape);
    }

}
