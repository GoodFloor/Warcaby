package pl.tp;

public class BasicVersion extends GameVersion {
    @Override
    void setBoard() {
        board = new ClassicBoard();
        board.resetBoard();
    }
    @Override
    public void start() {
        System.out.println("The Basic Game is on.");
    }

}
