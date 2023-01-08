package pl.tp.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
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
        // userInterface.getChildren().add(new MyButton("Test"));
        // pieces.getChildren().add(new MyButton("Testestest"));
        mainStage.setScene(mainScene);
        mainStage.show();

        // Scanner scanner = new Scanner(System.in);
        // String cmd = "";
        // while (!"EXT".equals(cmd)) {
        // try {
        // System.out.println("Wybierz polecenie(KIL/SWP):");
        // cmd = scanner.nextLine();
        // if("KIL".equals(cmd)) {
        // System.out.println("Wybierz pozycję X:");
        // int x = Integer.parseInt(scanner.nextLine());
        // System.out.println("Wybierz pozycję Y:");
        // int y = Integer.parseInt(scanner.nextLine());
        // pieces.killPiece(x, y);
        // }
        // else if("SWP".equals(cmd)) {
        // System.out.println("Wybierz pozycję X1:");
        // int x1 = Integer.parseInt(scanner.nextLine());
        // System.out.println("Wybierz pozycję Y1:");
        // int y1 = Integer.parseInt(scanner.nextLine());
        // System.out.println("Wybierz pozycję X2:");
        // int x2 = Integer.parseInt(scanner.nextLine());
        // System.out.println("Wybierz pozycję Y2:");
        // int y2 = Integer.parseInt(scanner.nextLine());
        // pieces.switchPlaces(x1, y1, x2, y2);
        // }
        // } catch (Exception e) {
        // System.out.println(e);
        // }
        // }
        // scanner.close();
        pieces.switchPlaces(1, 0, 1, 4);
        pieces.killPiece(2, 1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
