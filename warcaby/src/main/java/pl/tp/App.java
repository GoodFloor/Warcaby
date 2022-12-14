package pl.tp;

public class App {

    public static void main(String[] args) {
        VersionPicker factory = new VersionPicker();
        GameController game = factory.getGame(1);

        game.run();
    }
}
