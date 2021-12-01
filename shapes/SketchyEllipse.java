package sketchy.shapes;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;

public class SketchyEllipse {

    public SketchyEllipse(double x, double y, Pane pane){
        Ellipse ellipse = new Ellipse(x,y,10,10);
        pane.getChildren().add(ellipse);
    }
}
