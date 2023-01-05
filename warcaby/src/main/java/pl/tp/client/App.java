package pl.tp.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application{
    Pane root;

    @Override
    public void start(Stage mainStage) throws Exception {
        root = new Pane();
        BoardLayer board = new BoardLayer();
        PiecesLayer pieces = new PiecesLayer();
        Pane userInterface = new Pane();
        mainStage.setTitle("Warcaby");
        root.getChildren().addAll(board, pieces, userInterface);
        board.drawNew(8, 8);
        pieces.drawNew(8, 8);
        Scene mainScene = new Scene(root, 800, 800);
        //userInterface.getChildren().add(new MyButton("Test"));
        //pieces.getChildren().add(new MyButton("Testestest"));
        mainStage.setScene(mainScene);
        mainStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
