package sketchy.main;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PaneOrganizer {
    private BorderPane root;
    private VBox buttonPane;

    public PaneOrganizer(){
        this.root = new BorderPane();
        Control control = new Control(root);
        Canvas canvas = new Canvas(root);
    }

    public BorderPane getRoot(){
        return this.root;
    }


}
