package pl.tp;

/**
 * Klasa obsługująca działania na planszy
 * 
 * Klasa abstrakcyjna we wzorcu Template method
 * Kontroler we wzorcu MVC
 */
public abstract class BoardController {
    protected Board board;
    protected boolean isTurnOver;

    public abstract void resetBoard();

    public abstract void startNextTurn();

    public abstract String isGameOver();

    public abstract SquareStateEnum[][] translateBoard();

    public abstract void movePiece(String pos1, String pos2) throws IncorrectPositionException;

    BoardController() {
        board = new Board();
    }

    public boolean isTurnOver() {
        return isTurnOver;
    }

}
