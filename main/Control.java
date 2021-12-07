package sketchy.main;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Control {
    private VBox buttonPane;
    private Canvas canvas;
    private Color controlColor;

    public Control(BorderPane root, Canvas myCanvas){
        this.canvas = myCanvas;
        this.setUpButtonPane();
        root.setLeft(buttonPane);
        this.setUpOptions();
        //this.setUpActions();
        this.setUpOperations();
    }

    public void setUpButtonPane(){
        this.buttonPane = new VBox();
        this.buttonPane.setStyle("-fx-background-color: lightgrey");
        this.buttonPane.setPrefSize(200, 700); //height is pane height constant
        this.buttonPane.setAlignment(Pos.CENTER);
        this.buttonPane.setSpacing(10);
    }

    public void setUpOptions(){
        Label label1 = new Label("Drawing Options");
        ToggleGroup options = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Select Shape");
        rb1.setOnAction((ActionEvent e) -> this.canvas.setOption(Option.SELECT));
        rb1.setSelected(true);
        RadioButton rb2 = new RadioButton("Draw with Pen");
        rb2.setOnAction((ActionEvent e) -> this.canvas.setOption(Option.PEN));
        RadioButton rb3 = new RadioButton("Draw Rectangle");
        rb3.setOnAction((ActionEvent e) -> this.canvas.setOption(Option.RECTANGLE));
        RadioButton rb4 = new RadioButton("Draw Ellipse");
        rb4.setOnAction((ActionEvent e) -> this.canvas.setOption(Option.ELLIPSE));
        rb1.setToggleGroup(options);
        rb2.setToggleGroup(options);
        rb3.setToggleGroup(options);
        rb4.setToggleGroup(options);
        Label label2 = new Label("Set the Color");
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.GREY);
        colorPicker.setOnAction((ActionEvent e) -> canvas.setColor(colorPicker.getValue()));
        this.buttonPane.getChildren().addAll(label1, rb1, rb2, rb3, rb4, label2, colorPicker);
    //}

    //public void setUpActions(){
        Label label = new Label("Shape Actions");
        Button button1 = new Button("Fill");
        button1.setOnAction((ActionEvent e) -> this.canvas.fillColor(colorPicker.getValue()));
        Button button2 = new Button("Delete");
        button2.setOnAction((ActionEvent e) -> this.canvas.deleteShape());
        Button button3 = new Button("Raise");
        Button button4 = new Button("Lower");
        this.buttonPane.getChildren().addAll(label, button1, button2, button3, button4);
    }

    public void setUpOperations(){
        Label label = new Label("Operations");
        Button button1 = new Button("Undo");
        Button button2 = new Button("Redo");
        Button button3 = new Button("Save");
        Button button4 = new Button("Load");
        this.buttonPane.getChildren().addAll(label, button1, button2, button3, button4);
    }

}
