package pl.tp.client;

import pl.tp.SquareStateEnum;

public interface View {
    void close();
    void newBoard(int size);
    void drawBoard(SquareStateEnum[][] pieces);
    void printMessage(String message);
}
