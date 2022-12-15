package pl.tp;

public abstract class BoardController {
    protected Board board;

    public abstract void resetBoard();

    public abstract SquareStateEnum[][] translateBoard();

    public abstract void movePiece(String pos1, String pos2) throws IncorrectPositionException;

    BoardController() {
        board = new Board();
    }

}
