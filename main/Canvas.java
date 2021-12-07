package sketchy.main;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import sketchy.shapes.CurvedLine;
import sketchy.shapes.SketchyEllipse;
import sketchy.shapes.SketchyRectangle;
import sketchy.shapes.SketchyShape;
import java.util.Stack;

public class Canvas {
    private Pane canvasPane;
    private Option currOption;
    private Stack<SketchyShape> shapeStack;
    private SketchyShape selectedShape;
    private Color colorSelected;
    private Point2D prevLoc;
    private CurvedLine line;

    public Canvas(BorderPane root){
        this.canvasPane = new Pane();
        canvasPane.setStyle("-fx-background-color: white");
        root.setCenter(canvasPane);
        canvasPane.setOnMousePressed((e) -> this.handleMousePress(e));
        canvasPane.setOnMouseDragged((f) -> this.handleMouseDrag(f));
        canvasPane.setOnMouseReleased((g) -> this.handleMouseRelease(g));
        this.shapeStack = new Stack<>();
        this.currOption = Option.SELECT;
    }

    public void handleMousePress(MouseEvent e){ //turn into switch statement depending on different mouse presses?
        this.prevLoc = new Point2D(e.getX(),e.getY());
        switch(currOption) {
            case RECTANGLE:
                SketchyRectangle rect = new SketchyRectangle(this.prevLoc,canvasPane, this.colorSelected);
                this.shapeStack.push(rect);
                System.out.println(shapeStack.isEmpty());
                break;
            case ELLIPSE:
                SketchyEllipse ellipse = new SketchyEllipse(this.prevLoc,canvasPane, this.colorSelected);
                this.shapeStack.push(ellipse); //adding resize lines for setOnMouseDragged removes bugs? maybe
                break;
            case PEN:
                this.line = new CurvedLine(e.getX(),e.getY(),canvasPane);
                this.line.setColor(this.colorSelected);
                break;
            case SELECT:
                for (SketchyShape shape : this.shapeStack) {
                    if (shape.checkContains(this.rotatePoint(this.prevLoc,shape.getCenter(),shape.getRotate()))) {
                        shape.select();
                        this.selectedShape = shape;}
                    else shape.deselect();
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
                    if (!f.isControlDown() && !f.isShiftDown()){
                        this.selectedShape.translate(this.prevLoc,dragLoc);
                        this.prevLoc = new Point2D(f.getX(),f.getY());}
                    else if (!f.isShiftDown()){
                        this.selectedShape.rotate(this.prevLoc,dragLoc);
                    }
                    else {
                        this.selectedShape.resize(dragLoc); //add rotation
                    }
                }
                break;
            case PEN:
                line.addPoint(f.getX(),f.getY());
                break;
            case RECTANGLE:
                this.shapeStack.get(this.shapeStack.size()-1).resize(dragLoc);
            case ELLIPSE:
                this.shapeStack.get(this.shapeStack.size()-1).resize(dragLoc);
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
        this.selectedShape.fill(color);
    }

    public void deleteShape(){
        this.selectedShape.delete();
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
}
