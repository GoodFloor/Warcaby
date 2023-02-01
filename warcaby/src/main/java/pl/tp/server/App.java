package pl.tp.server;

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
        if (args.length != 1) {
            System.out.println("Podaj wersję warcabów do uruchomienia!");
            return;
        }
        HibernateUtil hibernate = new HibernateUtil();
        try {
            int version = Integer.parseInt(args[0]);
            VersionFactoryInterface factory = new VersionFactory();
            GameController game = factory.getGame(version);

            game.run();
        } catch (Exception e) {
            System.out.println("Niepoprawna wersja");
        }

        VersionFactoryInterface factory = new VersionFactory();
        GameController game = factory.getGame(3);

        game.run();
    }
}
