package sketchy.main;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sketchy.commands.Command;
import sketchy.commands.CreateCommand;
import sketchy.commands.DeleteCommand;
import sketchy.commands.FillCommand;
import sketchy.shapes.CurvedLine;
import sketchy.shapes.SketchyEllipse;
import sketchy.shapes.SketchyRectangle;
import sketchy.shapes.SketchyShape;

import java.util.ArrayList;
import java.util.Stack;

public class Canvas {
    private Pane canvasPane;
    private Option currOption;
    private ArrayList<SketchyShape> shapeList;
    private SketchyShape selectedShape;
    private Color colorSelected;
    private Point2D prevLoc;
    private CurvedLine line;
    private Stack<Command> undos;
    private Stack<Command> redos;

    public Canvas(BorderPane root){
        this.canvasPane = new Pane();
        canvasPane.setStyle("-fx-background-color: white");
        root.setCenter(canvasPane);
        canvasPane.setOnMousePressed((e) -> this.handleMousePress(e));
        canvasPane.setOnMouseDragged((f) -> this.handleMouseDrag(f));
        canvasPane.setOnMouseReleased((g) -> this.handleMouseRelease(g));
        this.shapeList = new ArrayList<>(); //make sure this is written correctly
        this.undos = new Stack<>();
        this.redos = new Stack<>();
        this.currOption = Option.SELECT;
    }

    public void handleMousePress(MouseEvent e){ //turn into switch statement depending on different mouse presses?
        this.prevLoc = new Point2D(e.getX(),e.getY());
        switch(currOption) {
            case RECTANGLE:
                SketchyRectangle rect = new SketchyRectangle(this.prevLoc,canvasPane, this.colorSelected);
                this.shapeList.add(rect);
                this.undos.push(new CreateCommand(rect,this.canvasPane.getChildren().size()-1, this.shapeList));
                break;
            case ELLIPSE:
                SketchyEllipse ellipse = new SketchyEllipse(this.prevLoc,canvasPane, this.colorSelected);
                this.shapeList.add(ellipse); //adding resize lines for setOnMouseDragged removes bugs? maybe
                break;
            case PEN:
                this.line = new CurvedLine(e.getX(),e.getY(),canvasPane);
                this.line.setColor(this.colorSelected);
                break;
            case SELECT:
                for (int i=this.shapeList.size()-1;i>=0;i--){
                //for (SketchyShape shape : this.shapeList) {
                    if (this.shapeList.get(i).checkContains(this.rotatePoint(this.prevLoc,this.shapeList.get(i).getCenter(),this.shapeList.get(i).getRotate()))) {
                        this.deselectAll();
                        this.shapeList.get(i).select();
                        this.selectedShape = this.shapeList.get(i);
                        i=-1; // gets rid of edge cases; why we can't use a for-each loop.
                    }
                    else this.shapeList.get(i).deselect();
                    //this.selectedShape = null;
                }
                break;
            default:
                break;
        }
    }

    public void handleMouseDrag(MouseEvent f){ //check guidelines for naming MouseEvents
        Point2D dragLoc = new Point2D(f.getX(),f.getY());
        switch(currOption) {
            case SELECT:
                if (this.selectedShape != null) {
                    if (!f.isControlDown() && !f.isShiftDown() && this.selectedShape.checkContains(this.rotatePoint(this.prevLoc,this.selectedShape.getCenter(),this.selectedShape.getRotate()))){
                        this.selectedShape.translate(this.prevLoc,dragLoc);
                        this.prevLoc = new Point2D(f.getX(),f.getY());}
                    else if (f.isControlDown()){
                        this.selectedShape.rotate(this.prevLoc,dragLoc);
                    }
                    else if (f.isShiftDown()){ //resizing does not require checkContains because it messes up the function (this is why we dont have it in the first if statememnt
                        this.selectedShape.resize(this.rotatePoint(dragLoc,this.selectedShape.getCenter(),this.selectedShape.getRotate())); //add rotation
                    }
                }
                break;
            case PEN:
                line.addPoint(f.getX(),f.getY());
                break;
            case RECTANGLE:
                this.shapeList.get(this.shapeList.size()-1).resize(dragLoc);
            case ELLIPSE:
                this.shapeList.get(this.shapeList.size()-1).resize(dragLoc);
            default:
                break;
        }
    }

    public void handleMouseRelease(MouseEvent g){
        //this.selectedShape = null;
    }

    public void setOption(Option option){
        this.currOption = option;
    }

    public void fillColor(Color color){
        this.undos.push(new FillCommand(this.selectedShape,color));
        this.selectedShape.fill(color);
    }

    public void deleteShape(){
        this.undos.push(new DeleteCommand(this.selectedShape,this.canvasPane.getChildren().size()-1, this.shapeList));
        this.selectedShape.delete();
        this.shapeList.remove(this.selectedShape);
    }

    public void setColor(Color color){
        this.colorSelected = color; }

    public Point2D rotatePoint(Point2D pointToRotate, Point2D rotateAround, double angle) {
        double sine = Math.sin(Math.toRadians(angle));
        double cosine = Math.cos(Math.toRadians(angle));
        Point2D point = new Point2D(pointToRotate.getX() - rotateAround.getX(), pointToRotate.getY()- rotateAround.getY());
        point = new Point2D(point.getX()*cosine + point.getY()*sine,-point.getX()*sine+point.getY()*cosine);
        point = new Point2D(point.getX()+rotateAround.getX(),point.getY()+rotateAround.getY());
        return point;
    }

    public void deselectAll(){
        for (SketchyShape shape: this.shapeList){
            shape.deselect();
            //this.selectedShape = null;
        }
    }

    public void raise() {
        int currShapeIndex = this.shapeList.indexOf(this.selectedShape);
        int nextShapeIndex = currShapeIndex + 1;
        int moveToIndex = currShapeIndex;
        if (nextShapeIndex < this.shapeList.size()) {
            SketchyShape nextShape = this.shapeList.get(nextShapeIndex);
            if (nextShape.getIndex() - this.selectedShape.getIndex() == 1) {
                moveToIndex += 1;
            }
        }
        int movePaneIndex = this.selectedShape.getIndex() + 1;
        if (movePaneIndex < this.canvasPane.getChildren().size()) {
            this.selectedShape.delete(); //removes graphically
            this.selectedShape.addToPane(movePaneIndex); //adds graphically
            this.shapeList.remove(this.selectedShape); //removes logically
            this.shapeList.add(moveToIndex, this.selectedShape); //adds logically, depending on the index calculated above.
        }
    }

    public void lower(){
        int currShapeIndex = this.shapeList.indexOf(this.selectedShape);
        int prevShapeIndex = currShapeIndex - 1;
        int moveToIndex = currShapeIndex;
        if (prevShapeIndex >=0){
            SketchyShape prevShape = this.shapeList.get(prevShapeIndex);
            if (this.selectedShape.getIndex() - prevShape.getIndex() == 1){
                moveToIndex += -1;
            }
        }
        int movePaneIndex = this.selectedShape.getIndex() - 1;
        if (movePaneIndex >= 0) {
            this.selectedShape.delete();
            this.selectedShape.addToPane(movePaneIndex);
            this.shapeList.remove(this.selectedShape);
            this.shapeList.add(moveToIndex, this.selectedShape);
        }
    }

    public void undoIsPressed(){
        if (!undos.isEmpty()) {
            this.undos.peek().undo();
            this.redos.push(undos.pop());
        }
    }

    public void redoIsPressed(){
        if (!redos.isEmpty()) {
            this.redos.peek().redo();
            this.undos.push(redos.pop());
        }
    }
}
