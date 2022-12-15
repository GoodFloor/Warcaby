package pl.tp;

public abstract class GameController {
    protected BoardController boardController;
    protected GameView gameView;
    protected Boolean isGameRunning;
    protected String startMessage;

    abstract void setBoard();

    abstract void setStartMessage();

    public GameController() {
        this.gameView = new TerminalView();
        isGameRunning = false;
        startMessage = "The Game is on";
    }

    public void run() {
        setBoard();
        isGameRunning = true;
        gameView.printMessage(startMessage);
        gameView.start();

        while (isGameRunning) {
            gameView.printBoard(boardController.translateBoard());
            String move[] = gameView.getMove();
            try {
                boardController.movePiece(move[0], move[1]);
            } catch (IncorrectPositionException e) {
                gameView.printException(e);
            }
        }

        gameView.end();
    }
}
