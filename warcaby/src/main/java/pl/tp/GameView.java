package pl.tp;

public interface GameView {
    void showMessage(String message);

    void printBoard(SquareStateEnum[][] boardContent);
}
