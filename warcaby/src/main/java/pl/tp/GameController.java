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
                gameView.printMessage("Błędny ruch - spróbuj ponownie");
                continue;
            }
            isWhiteTurn = !isWhiteTurn;
        }

        gameView.end();
    }
}
