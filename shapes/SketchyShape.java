package sketchy.shapes;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public interface SketchyShape {

    void select();
    void deselect();
    boolean checkContains(Point2D point);
    void fill(Color color);
    void delete();
    void move(double x, double y);
}
