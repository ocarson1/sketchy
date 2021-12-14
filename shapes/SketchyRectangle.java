package sketchy.shapes;

import cs15.fnl.sketchySupport.CS15FileIO;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.lang.Math;

public class SketchyRectangle implements SketchyShape {
    private Rectangle rect;
    private Pane pane;

    public SketchyRectangle(Point2D point, Pane myPane, Color color){
        this.rect = new Rectangle(point.getX(), point.getY(), 0,0);
        this.rect.setFill(color);
        this.pane = myPane;
        this.create(this.pane.getChildren().size());
    }

    @Override
    public void create(int paneIndex) { //takes this index in so that undo can create preserve a shape's index.
        this.pane.getChildren().add(paneIndex,this.rect);
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
        this.pane.getChildren().remove(this.rect);
    }

    @Override
    public void translate(Point2D prev, Point2D curr) {
        double dx = curr.getX() - prev.getX();
        double dy = curr.getY() - prev.getY();
        this.rect.setX(this.rect.getX()+dx);
        this.rect.setY(this.rect.getY()+dy);
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
    public void setRotate(double angle){
        this.rect.setRotate(angle);
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
        Point2D center = this.getCenter();
        double dx = Math.abs(point.getX() - center.getX()) - this.rect.getWidth()/2;
        double dy = Math.abs(point.getY() - center.getY()) - this.rect.getHeight()/2;
        this.expandX(dx);
        this.expandY(dy);
    }

    public void expandX(double n){
        this.rect.setX((this.getX() - n));
        this.rect.setWidth((this.rect.getWidth() + 2*n));
    }

    public void expandY(double n){
        this.rect.setY(this.getY() - n);
        this.rect.setHeight(this.rect.getHeight() + 2*n);
    }

    @Override
    public int getIndex() {
        return this.pane.getChildren().indexOf(this.rect);
    }

    @Override
    public Color getColor() {
        return (Color) this.rect.getFill();
    }

    @Override
    public void save(CS15FileIO io){};








    //*******************


    public String getType(){
        return "rect";
    }


    public double getRed(){
        return ((Color) this.rect.getFill()).getRed();
    }


    public double getGreen(){
        return ((Color) this.rect.getFill()).getGreen();
    }


    public double getBlue(){
        return ((Color) this.rect.getFill()).getBlue();
    }


    public double getPoints(int i) {
        return 1;
    }


    public double getSize(){
        return 1;
    }
}
