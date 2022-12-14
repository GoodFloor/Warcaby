package pl.tp;

public abstract class GameController {
    protected BoardController boardController;
    protected GameView gameView;

    abstract void setBoard();

    void run() {
        setBoard();
        System.out.println("The Game is on.");
    }
}
