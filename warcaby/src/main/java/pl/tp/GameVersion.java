package pl.tp;

public abstract class GameVersion {
    protected ClassicBoard board;
    abstract void setBoard();
    void start() {
        setBoard();
        System.out.println("The Game is on.");
    }
}
