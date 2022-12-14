package pl.tp;

public abstract class BoardController {
    protected Board board;

    public abstract void resetBoard();

    public abstract SquareStateEnum[][] translateBoard();

    BoardController() {
        board = new Board();
    }

}
