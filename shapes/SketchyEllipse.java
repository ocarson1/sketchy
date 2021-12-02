package sketchy.shapes;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class SketchyEllipse implements SketchyShape {
    private Ellipse ellipse;
    private Pane pane;

    public SketchyEllipse(double x, double y, Pane myPane){
        this.ellipse = new Ellipse(x,y,10,10);
        this.ellipse.setFill(Color.GREENYELLOW);
        this.pane = myPane;
        this.pane.getChildren().add(ellipse);
    }

    @Override
    public void select(){
            this.ellipse.setStroke(Color.BLACK);
            this.ellipse.setStrokeWidth(5);
    }

    @Override
    public void deselect(){
        this.ellipse.setStroke(null);
    }

    @Override
    public boolean checkContains(Point2D point){
        return this.ellipse.contains(point);
    }

    @Override
    public void fill(Color color){
        this.ellipse.setFill(color);
    }

    @Override
    public void delete(){
        this.pane.getChildren().remove(ellipse);
    }
}
