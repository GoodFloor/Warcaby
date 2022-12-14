package pl.tp;

public abstract class GameController {
    protected BoardController boardController;
    abstract void setBoard();
    void start() {
        setBoard();
        System.out.println("The Game is on.");
    }
}
