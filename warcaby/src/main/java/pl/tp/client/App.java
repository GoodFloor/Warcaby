package pl.tp.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class App extends Application{
    Pane pane;

    @Override
    public void start(Stage mainStage) throws Exception {
        pane = new Pane();
        mainStage.setTitle("Warcaby");
        Scene mainScene = new Scene(pane, 800, 800);
        mainStage.setScene(mainScene);
        mainStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
