package pl.tp.server;

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
        // this.gameView = new TerminalView();
        this.gameView = new SocketView();
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
        gameView.printMessage(startMessage, -1);
        gameView.start();
        boolean firstTry = true;

        while (isGameRunning) {
            gameView.printBoard(boardController.translateBoard());
            if (firstTry && isWhiteTurn) {
                gameView.printMessage("Ruch białych", -1);
                gameView.printMessage("Twój ruch", 1);
                gameView.printMessage("Czekanie na przeciwnika", 2);
            }
            else if (firstTry) {
                gameView.printMessage("Ruch czarnych", -1);
                gameView.printMessage("Twój ruch", 2);
                gameView.printMessage("Czekanie na przeciwnika", 1);
            }
            firstTry = false;
            String move[] = gameView.getMove(isWhiteTurn);
            try {
                boardController.movePiece(move[0], move[1]);
            } catch (IncorrectPositionException e) {
                if (isWhiteTurn) {
                    gameView.printMessage("Błędny ruch - spróbuj ponownie", 1);                    
                }
                else {
                    gameView.printMessage("Błędny ruch - spróbuj ponownie", 2);
                }
                continue;
            }
            if (boardController.isGameOver() != null) {
                break;
            } else if (boardController.isTurnOver()) {
                firstTry = true;
                isWhiteTurn = !isWhiteTurn;
                boardController.startNextTurn();
            }
        }

        gameView.end();
    }
}
