package sketchy.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * It's time for Sketchy! This is the App class to get things started.
 *
 * Your job is to fill in the start method!
 *
 * Class comments here...
 */


public class App extends Application {

  @Override
  public void start(Stage stage) {
    // Create top-level object, set up the scene, and show the stage here.
    PaneOrganizer main = new PaneOrganizer();
    Scene scene = new Scene(main.getRoot(), 1000, 700);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] argv) {
    // launch is a method inherited from Application
    launch(argv);
  }
}
