package sketchy.shapes;

import cs15.fnl.sketchySupport.CS15FileIO;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class SketchyEllipse implements SketchyShape {
    private Ellipse ellipse;
    private Pane pane;

    public SketchyEllipse(Point2D point, Pane myPane, Color color){
        this.ellipse = new Ellipse(point.getX(), point.getY(),0,0);
        this.ellipse.setFill(color);
        this.pane = myPane;
        this.create(this.pane.getChildren().size());
    }

    @Override
    public void create(int paneIndex){
        this.pane.getChildren().add(paneIndex, this.ellipse);
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
        this.pane.getChildren().remove(this.ellipse);
    }

    @Override
    public void translate(Point2D prev, Point2D curr) {
        double dx = curr.getX() - prev.getX();
        double dy = curr.getY() - prev.getY();
        this.ellipse.setCenterX(this.ellipse.getCenterX()+dx);
        this.ellipse.setCenterY(this.ellipse.getCenterY()+dy);
    }

    @Override
    public Point2D getCenter(){
        return new Point2D(this.ellipse.getCenterX(),this.ellipse.getCenterY());
    }

    @Override
    public double getRotate(){
        return this.ellipse.getRotate();
    }

    @Override
    public void setRotate(double angle){
        this.ellipse.setRotate(angle);
    }


    public double getX(){
        System.out.println(this.ellipse.getCenterX());
        return this.ellipse.getCenterX();
    }

    public double getY(){
        System.out.println(this.ellipse.getCenterY());
        return this.ellipse.getCenterY();
    }

    @Override
    public void rotate(Point2D prev, Point2D curr) {
        Point2D center = this.getCenter();
        double theta = Math.atan2(prev.getY()-center.getY(),prev.getX()-center.getX()) - Math.atan2(curr.getY() - center.getY(), curr.getX() - center.getX());
        System.out.println(theta);
        this.ellipse.setRotate(Math.toDegrees(-1*theta));
    }

    @Override
    public void resize(Point2D point){
        double dx = point.getX() - this.getCenter().getX();
        double dy = point.getY() - this.getCenter().getY();
        this.ellipse.setRadiusX(Math.abs(dx));
        this.ellipse.setRadiusY(Math.abs(dy));
    }

    @Override
    public int getIndex() {
        return this.pane.getChildren().indexOf(this.ellipse);
    }

    @Override
    public Color getColor() {
        return (Color) this.ellipse.getFill();
    }

    @Override
    public void save(CS15FileIO io){};

    //**************************************

    public String getType(){
        return "ellipse";
    }


    public double getRed(){
        return ((Color) this.ellipse.getFill()).getRed();
    }


    public double getGreen(){
        return ((Color) this.ellipse.getFill()).getGreen();
    }


    public double getBlue(){
        return ((Color) this.ellipse.getFill()).getBlue();
    }


    public double getPoints(int i) {
        return 1;
    }


    public double getSize(){
        return 1;
    }
}
