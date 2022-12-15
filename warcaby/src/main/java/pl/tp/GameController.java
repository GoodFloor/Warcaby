package pl.tp;

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
            String move[] = gameView.getMove();
            boardController.movePiece(move[0], move[1]);
        }

        gameView.closeScanner();

    }
}
