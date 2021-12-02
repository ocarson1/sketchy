package sketchy.shapes;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class CurvedLine {
    private Polyline line;

    public CurvedLine(double x, double y, Pane pane){
        this.line = new Polyline(x,y);
        pane.getChildren().add(line);
    }

    public void addPoint(double x, double y){
        this.line.getPoints().add(x);
        this.line.getPoints().add(y);
    }

    public void setColor(Color color){
        this.line.setStroke(color);
    }

}
