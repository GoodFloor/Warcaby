package pl.tp;

import java.util.Scanner;

public abstract class GameController {
    protected BoardController boardController;
    protected GameView gameView;
    protected Boolean isGameRunning;

    abstract void setBoard();

    public GameController() {
        this.gameView = new TerminalView();
        isGameRunning = false;
    }

    public void run() {
        setBoard();
        isGameRunning = true;
        gameView.printMessage("The Game is on");

        gameView.openScanner();

        while (isGameRunning) {
            gameView.printBoard(boardController.translateBoard());
            gameView.getMove();
        }

        gameView.closeScanner();

    }
}
