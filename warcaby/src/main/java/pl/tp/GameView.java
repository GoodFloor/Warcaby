package pl.tp;

public interface GameView {
    void printMessage(String message);

    void printBoard(SquareStateEnum[][] boardContent);

    String[] getMove();

    public void closeScanner();

    public void openScanner();

}
