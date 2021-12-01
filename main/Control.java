package sketchy.main;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class Control {
    private VBox buttonPane;

    public Control(BorderPane root){
        this.setUpButtonPane();
        root.setLeft(buttonPane);
        this.setUpOptions();
        this.setUpActions();
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
        RadioButton radButton1 = new RadioButton("Select Shape");
        RadioButton radButton2 = new RadioButton("Draw with Pen");
        RadioButton radButton3 = new RadioButton("Draw Rectangle");
        RadioButton radButton4 = new RadioButton("Draw Ellipse");
        Label label2 = new Label("Set the Color");
        ColorPicker colorPicker = new ColorPicker();
        this.buttonPane.getChildren().addAll(label1, radButton1, radButton2, radButton3, radButton4, label2, colorPicker);
    }

    public void setUpActions(){
        Label label = new Label("Shape Actions");
        Button button1 = new Button("Fill");
        Button button2 = new Button("Delete");
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
