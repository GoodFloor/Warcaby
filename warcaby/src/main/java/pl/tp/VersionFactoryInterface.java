package pl.tp;

/**
 * Twórca we wzorcu Factory - wybiera konkretny rodzaj gry
 */
public interface VersionFactoryInterface {
    /**
     * @param versionNr Numer wersji gry do uruchomienia.
     * @return Zwraca obiekt GameController - kontroler uruchamiający grę
     *         konkretnego typu.
     */
    public GameController getGame(int versionNr);
}
