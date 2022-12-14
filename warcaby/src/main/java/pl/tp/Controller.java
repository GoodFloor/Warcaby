package pl.tp;

public class Controller {
    private GameVersion game;
    private GameView view;

    public void setGame(GameVersion game) {
        this.game = game;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void run() {
        System.out.println("test");
    }

}
