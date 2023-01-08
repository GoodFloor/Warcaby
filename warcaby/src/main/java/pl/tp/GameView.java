package pl.tp;

/**
 * Klasa przesyłające informacje użytkownikowi
 * 
 * View we wzorcu MVC
 */
public interface GameView {

    /**
     * Wyświetlenie komunikatu
     * 
     * @param message komunikat do wyświetlenia
     */
    void printMessage(String message);

    /**
     * Wyświetlenie komunikatu o błędzie
     * 
     * @param e błąd do wyświetlenia
     */
    void printException(Exception e);

    /**
     * Wyświetlenie planszy
     * 
     * @param boardContent tablica zawierająca dane planszy
     */
    void printBoard(SquareStateEnum[][] boardContent);

    /**
     * Pobranie informacji o ruchu
     * 
     * @return tablica zawierająca pozycję początkową i docelową pionka
     */
    String[] getMove();

    /**
     * Rozpoczęcie gry u użytkownika
     */
    void start();

    /**
     * Zakończenie gry u użytkownika
     */
    void end();

}
