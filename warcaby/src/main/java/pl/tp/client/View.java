package pl.tp.client;

import pl.tp.SquareStateEnum;

public interface View {
    void close();
    void newBoard(int size);
    void drawBoard(SquareStateEnum[][] pieces);
    void printMessage(String message);
    String[] getMove();
    void endMove();
    void drawProposed();
    boolean getDrawResponse();
    void endDrawDiscussion();
    void endGame(String message);
    boolean isExited();
}
