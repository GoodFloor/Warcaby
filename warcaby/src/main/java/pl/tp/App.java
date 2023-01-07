package pl.tp;

/**
 * Klasa uruchamiająca serwer
 */
public class App {

    /**
     * Uruchomienie aplikacji
     * 
     * @param args tablica argumentów przekazywanych do aplikacji
     */
    public static void main(String[] args) {
        VersionFactoryInterface factory = new VersionFactory();
        GameController game = factory.getGame(1);

        game.run();
    }
}
