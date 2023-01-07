package pl.tp;

/**
 * Klasa obsługująca konkretną rozgrywkę
 * 
 * Produkt we wzorcu Factory
 * Klasa abstrakcyjna we wzorcu Template method
 * Kontroler we wzorcu MVC
 */
public abstract class GameController {
    protected BoardController boardController;
    protected GameView gameView;
    protected Boolean isGameRunning;
    protected Boolean isWhiteTurn;
    protected String startMessage;

    /**
     * Ustawienie konkretnego rodzaju planszy
     */
    abstract void setBoard();

    /**
     * Ustawienie wiadomości początkowej
     */
    abstract void setStartMessage();

    /**
     * Konstruktor ustawiający parametry początkowe
     */
    public GameController() {
        this.gameView = new TerminalView();
        isGameRunning = false;
    }

    /**
     * Funkcja uruchamiająca rozgrywkę - główna pętla odpowiedzialna za grę
     */
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
            if (boardController.isGameOver() != null) {
                break;
            } else if (boardController.isTurnOver()) {
                isWhiteTurn = !isWhiteTurn;
                boardController.startNextTurn();
            }
        }

        gameView.end();
    }
}
