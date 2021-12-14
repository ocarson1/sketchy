package sketchy.commands;

import javafx.scene.layout.Pane;
import sketchy.shapes.SketchyShape;

import java.util.ArrayList;

public class RaiseCommand implements Command{
    private SketchyShape myShape;
    private ArrayList<SketchyShape> myList;
    private Pane myPane;

    public RaiseCommand(SketchyShape shape, ArrayList<SketchyShape> shapeList,Pane pane){
        this.myShape = shape;
        this.myList = shapeList;
        this.myPane = pane;
    }

    @Override //copies lower() code from Canvas
    public void undo(){
        int currShapeIndex = this.myList.indexOf(this.myShape);
        int prevShapeIndex = currShapeIndex - 1;
        int moveToIndex = currShapeIndex;
        if (prevShapeIndex >=0){
            SketchyShape prevShape = this.myList.get(prevShapeIndex);
            if (this.myShape.getIndex() - prevShape.getIndex() == 1){
                moveToIndex += -1;
            }
        }
        int movePaneIndex = this.myShape.getIndex() - 1;
        if (movePaneIndex >= 0) {
            this.myShape.delete();
            this.myShape.create(movePaneIndex);
            this.myList.remove(this.myShape);
            this.myList.add(moveToIndex, this.myShape);
        }
    }

    @Override //copies raise() code from Canvas
    public void redo(){
        int currShapeIndex = this.myList.indexOf(this.myShape);
        int nextShapeIndex = currShapeIndex + 1;
        int moveToIndex = currShapeIndex;
        if (nextShapeIndex < this.myList.size()) {
            SketchyShape nextShape = this.myList.get(nextShapeIndex);
            if (nextShape.getIndex() - this.myShape.getIndex() == 1) {
                moveToIndex += 1;
            }
        }
        int movePaneIndex = this.myShape.getIndex() + 1;
        if (movePaneIndex < this.myPane.getChildren().size()) {
            this.myShape.delete(); //removes graphically
            this.myShape.create(movePaneIndex); //adds graphically
            this.myList.remove(this.myShape); //removes logically
            this.myList.add(moveToIndex, this.myShape); //adds logically, depending on the index calculated above.
        }
    }
}
