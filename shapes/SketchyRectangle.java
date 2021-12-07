package sketchy.shapes;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.lang.Math;

public class SketchyRectangle implements SketchyShape {
    private Rectangle rect;
    private Pane pane;

    public SketchyRectangle(Point2D point, Pane myPane){
        this.rect = new Rectangle(point.getX(), point.getY(), 100,100);
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
    public void translate(Point2D prev, Point2D curr) {
        double dx = curr.getX() - prev.getX();
        double dy = curr.getY() - prev.getY();
        this.rect.setX(this.rect.getX()+dx);
        //this.rect.setX(this.rect.getX() + dx);
        this.rect.setY(this.rect.getY()+dy);
        //this.rect.setY(this.rect.getY() + dy);
    }


    @Override
    public void rotate(Point2D prev, Point2D curr) {
        Point2D center = this.getCenter();
        double theta = Math.atan2(prev.getY()-center.getY(),prev.getX()-center.getX()) - Math.atan2(curr.getY() - center.getY(), curr.getX() - center.getX());
        this.rect.setRotate(Math.toDegrees(-1*theta));
    }

    @Override
    public double getRotate(){
        return this.rect.getRotate();
    }

    @Override
    public double getX(){
        return this.rect.getX();

    }

    @Override
    public double getY(){
        return this.rect.getY();
    }

    @Override
    public Point2D getCenter(){
        return new Point2D(this.getX()+this.rect.getWidth()/2,this.getY()+this.rect.getHeight()/2);
    }

    @Override
    public void resize(Point2D point){
        double prevWidth = this.rect.getWidth();
        double prevHeight = this.rect.getHeight();
        double dx = point.getX() - this.getCenter().getX();
        //System.out.println(dx);
        double dy = point.getY() - this.getCenter().getY();
        this.rect.setWidth(2*dx);
        this.rect.setHeight(2*dy);
        System.out.println((this.rect.getWidth()-prevWidth)));
        //this.rect.setX(this.rect.getX() - (this.rect.getWidth()-prevWidth));
        //this.rect.setY(this.rect.getY() - (this.rect.getHeight()-prevHeight));

        //this.rect.setX(this.getX()-(this.rect.getWidth()) - prevWidth);
        //this.rect.setX(this.getX() - (this.getX() + this.getCenter().getX()));
        //this.rect.setY(this.getY() - (this.getY() + this.getCenter().getY()));
    }
}
