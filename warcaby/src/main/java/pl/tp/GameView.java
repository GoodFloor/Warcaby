package pl.tp;

public interface GameView {
    void printMessage(String message);

    void printException(Exception e);

    void printBoard(SquareStateEnum[][] boardContent);

    String[] getMove();

    void start();

    void end();

}
