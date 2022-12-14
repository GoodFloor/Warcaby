package pl.tp;

public abstract class GameController {
    protected BoardController boardController;
    protected GameView gameView;

    abstract void setBoard();

    public GameController() {
        this.gameView = new TerminalView();
    }

    public void run() {
        // setBoard();
        gameView.showMessage("The Game is on");
    }
}
