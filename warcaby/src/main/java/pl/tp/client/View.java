package pl.tp.client;

import pl.tp.SquareStateEnum;

public interface View {
    void updatePieces(SquareStateEnum[][] boardContent);
    void end();
    String[] getMove();
    void printMessage(String message);
    void printException(Exception e);
}
