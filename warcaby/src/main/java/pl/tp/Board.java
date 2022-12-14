package pl.tp;

public abstract class Board {
    protected int width;
    protected int height;
    protected Piece[][] board;
    abstract void resetBoard();
}
