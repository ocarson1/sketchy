package sketchy.shapes;

import cs15.fnl.sketchySupport.CS15FileIO;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polyline;

public class CurvedLine implements Saveable{
    private Polyline line;
    private Pane myPane;

    public CurvedLine(Color color, double x, double y, Pane pane){ //color comes first so that it matches FileIO
        this.line = new Polyline(x,y);
        this.line.setStroke(color);
        this.line.setStrokeWidth(4);
        this.myPane = pane;
        this.create(this.myPane.getChildren().size());
        System.out.println(this.getStroke());
        System.out.println(this.getRed());
        System.out.println(this.getBlue());
        System.out.println(this.getGreen());
    }

    public void create(int paneIndex) { //takes this index in so that undo can create preserve a shape's index.
        this.myPane.getChildren().add(paneIndex,this.line);
    }

    public void addPoint(double x, double y){
        this.line.getPoints().add(x);
        this.line.getPoints().add(y);
    }

    public void delete() {
        this.myPane.getChildren().remove(this.line);
    }

    public int getIndex() {
        return this.myPane.getChildren().indexOf(this.line);
    }

    @Override
    public void save(CS15FileIO io){
        io.writeString("line");
        io.writeInt(this.getRed());
        io.writeInt(this.getBlue());
        io.writeInt(this.getGreen());
        for (double point: this.line.getPoints()){
            io.writeDouble(point);
        }
    }








//*********************

    public String getType(){
        return "line";
    }



    public int getRed(){
        return (int)(((Color)(this.getStroke())).getRed()*255);
    }

    public Paint getStroke(){
        return this.line.getStroke();
    }

    public int getGreen(){
        return (int)(((Color)(this.getStroke())).getGreen()*255);
    }



    public int getBlue() {
        return (int) (((Color) (this.getStroke())).getBlue() * 255);
    }

    public double getPoints(int i){
        return this.line.getPoints().get(i);
    }


    public double getSize(){
        return this.line.getPoints().size();
    }
}
