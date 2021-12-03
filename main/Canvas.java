package sketchy.main;

import javafx.event.EventHandler;
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

    public Canvas(BorderPane root){
        this.canvasPane = new Pane();
        canvasPane.setStyle("-fx-background-color: white");
        root.setCenter(canvasPane);
        canvasPane.setOnMousePressed((e) -> this.handleMousePress(e));
        this.shapeStack = new Stack<>();
        this.currOption = Option.SELECT;
        this.colorSelected = Color.BLACK;
    }

    public void handleMousePress(MouseEvent e){ //turn into switch statement depending on different mouse presses?
        Point2D point = new Point2D(e.getX(),e.getY());
        switch(currOption) {
            case RECTANGLE:
                SketchyRectangle rect = new SketchyRectangle(e.getX(), e.getY(), canvasPane);
                this.shapeStack.push(rect);
                System.out.println(shapeStack.isEmpty());
                break;
            case ELLIPSE:
                SketchyEllipse ellipse = new SketchyEllipse(e.getX(),e.getY(),canvasPane);
                this.shapeStack.push(ellipse); //adding resize lines for setOnMouseDragged removes bugs? maybe
                break;
            case LINE:
                CurvedLine line = new CurvedLine(e.getX(),e.getY(),canvasPane);
                line.setColor(this.colorSelected);
                canvasPane.setOnMouseDragged((f) -> line.addPoint(f.getX(),f.getY()));
                break;
            case SELECT:
                for (SketchyShape shape : this.shapeStack) {
                    if (shape.checkContains(point)) {
                        shape.select();
                        this.selectedShape = shape;
                    } else shape.deselect();
                }
                canvasPane.setOnMouseDragged((f) -> this.selectedShape.move(f.getX(),f.getY()));

                //}
                break;
            default:
                break;
        }

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
}
