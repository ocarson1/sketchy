package sketchy.shapes;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SketchyRectangle implements SketchyShape {
    private Rectangle rect;
    private Pane pane;

    public SketchyRectangle(double x, double y, Pane myPane){
        this.rect = new Rectangle(x, y, 100,100);
        this.rect.setFill(Color.BLUE);
        this.pane = myPane;
        this.pane.getChildren().add(rect);
    }

    @Override
    public void select(){
        this.rect.setStroke(Color.BLACK);
        this.rect.setStrokeWidth(5);
    }

    @Override
    public void deselect(){
        this.rect.setStrokeWidth(0);
    }

    @Override
    public boolean checkContains(Point2D point){
        return this.rect.contains(point);
    }

    @Override
    public void fill(Color color){
        this.rect.setFill(color);
    }

    @Override
    public void delete(){
        this.pane.getChildren().remove(rect);
    }

    @Override
    public void move(double x, double y) {
        this.rect.setX(x);
        this.rect.setY(y);
    }
}
