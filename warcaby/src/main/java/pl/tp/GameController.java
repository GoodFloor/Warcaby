package pl.tp;

public abstract class GameController {
    protected BoardController boardController;
    protected GameView gameView;
    protected Boolean isGameRunning;
    protected Boolean isWhiteTurn;
    protected String startMessage;

    abstract void setBoard();

    abstract void setStartMessage();

    public GameController() {
        this.gameView = new TerminalView();
        isGameRunning = false;
    }

    public void run() {
        setBoard();
        setStartMessage();
        isGameRunning = true;
        isWhiteTurn = true;
        gameView.printMessage(startMessage);
        gameView.start();
        boardController.startNextTurn();

        while (isGameRunning) {
            gameView.printBoard(boardController.translateBoard());
            if (isWhiteTurn)
                gameView.printMessage("Ruch białych");
            else
                gameView.printMessage("Ruch czarnych");

            String move[] = gameView.getMove();
            try {
                boardController.movePiece(move[0], move[1]);
            } catch (IncorrectPositionException e) {
                gameView.printException(e);
                continue;
            }
            if(boardController.isGameOver() != null) {
                break;
            }
            else if(boardController.isTurnOver()) {
                isWhiteTurn = !isWhiteTurn;
                boardController.startNextTurn();
            }
        }

        gameView.end();
    }
}
