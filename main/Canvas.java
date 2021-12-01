package sketchy.main;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sketchy.shapes.SketchyEllipse;
import sketchy.shapes.SketchyRectangle;

public class Canvas {
    private Pane canvasPane;
    private Option currOption;

    public Canvas(BorderPane root){

        this.canvasPane = new Pane();
        this.currOption = Option.RECTANGLE;
        canvasPane.setStyle("-fx-background-color: white");
        root.setCenter(canvasPane);
        canvasPane.setOnMousePressed((e) -> this.handleMousePress(e));
    }

    public void handleMousePress(MouseEvent e){ //turn into switch statement depending on different mouse presses?
        switch(currOption) {
            case RECTANGLE:
                SketchyRectangle rect = new SketchyRectangle(e.getX(), e.getY(), canvasPane);
                currOption = Option.ELLIPSE;
                break;
            case ELLIPSE:
                SketchyEllipse ellipse = new SketchyEllipse(e.getX(),e.getY(),canvasPane);
                break;
            default:
                break;
        }

    }
}
