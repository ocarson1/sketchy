package sketchy.shapes;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public interface SketchyShape extends Saveable{

    void create(int paneIndex);
    void select();
    void deselect();
    boolean checkContains(Point2D point);
    void fill(Color color);
    void delete();
    void translate(Point2D prev, Point2D curr);
    double getX();
    double getY();
    void rotate(Point2D rotatePrev, Point2D rotateCurr);
    Point2D getCenter();
    double getRotate();
    void setRotate(double angle);
    void resize(Point2D pointToResize);
    int getIndex();
    Color getColor();

}
