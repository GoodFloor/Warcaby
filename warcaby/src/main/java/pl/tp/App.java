package pl.tp;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        GameInterface game = new Game();
        game.setGameVersion(1);
        game.run();
    }
}
