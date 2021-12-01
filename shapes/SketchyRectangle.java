package sketchy.shapes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SketchyRectangle {

    public SketchyRectangle(double x, double y, Pane pane){
        Rectangle rect = new Rectangle(x, y, 100,100);
        pane.getChildren().add(rect);
    }
}
