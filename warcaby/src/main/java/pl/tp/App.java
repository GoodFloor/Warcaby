package pl.tp;

/**
 * Klasa uruchamiająca serwer
 */
public class App {

    /**
     * Funkcja main
     * 
     * @param args tablica argumentów przekazywanych do aplikacji
     */
    public static void main(String[] args) {
        VersionPicker factory = new VersionPicker();
        GameController game = factory.getGame(1);

        game.run();
    }
}
