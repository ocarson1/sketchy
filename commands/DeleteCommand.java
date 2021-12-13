package sketchy.commands;

import sketchy.shapes.SketchyShape;

import java.util.ArrayList;

public class DeleteCommand implements Command{
    private SketchyShape myShape;
    private int paneIndex;
    private int arrayIndex;
    private ArrayList<SketchyShape> myList;

    public DeleteCommand(SketchyShape shape, int index, ArrayList shapeList) {
        this.myShape = shape; // all of these private variables are being saved so that they are stored within the instance.
        this.paneIndex = index; //maybe edit to take in the entire arraylist as a parameter and establish the first on on the area list as the shape?
        this.arrayIndex = shapeList.size()-1;
        this.myList = shapeList;
    }

    public void undo() { //bug here is because delete changing the size of everything when it is called in canvas)
        this.myShape.create(this.paneIndex-1); //shifts everything in front of it in the list of children forwards
        this.myList.add(this.arrayIndex, this.myShape);
    }


    public void redo(){
        this.myShape.delete();
        this.myList.remove(this.myShape);
    }

}
