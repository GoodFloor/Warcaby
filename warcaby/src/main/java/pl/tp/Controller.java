package pl.tp;

public class Controller {
    private GameController game;
    private GameView view;

    public void setGame(GameController game) {
        this.game = game;
    }

    public void setView(GameView view) {
        this.view = view;
    }

    public void run() {
        System.out.println("test");
    }

}
