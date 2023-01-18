package pl.tp.client;

import pl.tp.SquareStateEnum;

/**
 * Interfejs do obsługi widoku w modelu MVC dla klasy App
 */
public interface View {
    /**
     * Funkcja do zamykania okna widoku
     */
    void close();
    /**
     * Przekazanie danych o nowych rozmiarach planszy
     * @param size rozmiar planszy
     */
    void newBoard(int size);
    /**
     * Renderowanie aktualnej planszy
     * @param pieces tablica stanów poszczególnych pól na planszy
     */
    void drawBoard(SquareStateEnum[][] pieces);
    /**
     * Wyświetlanie użytkownikowi komunikatu
     * @param message
     */
    void printMessage(String message);
    /**
     * Oczekiwanie na podanie przez użytkownika chcianego ruchu
     * @return tablica z pozycją początkową i końcową pionka
     */
    String[] getMove();
    /**
     * Zakończenie ruchu użytkownika i uniemożliwienie mu próby poruszenia się
     */
    void endMove();
    /**
     * Obsługa złożonej przez drugiego gracza propozycji uznania remisu 
     */
    void drawProposed();
    /**
     * Uzyskanie odpowiedzi odnośnie chęci uznania przez gracza remisu
     * @return czy uznano remis
     */
    boolean getDrawResponse();
    /**
     * Zakończenie gry
     * @param message Wiadomość wyświetlona przed zamknięciem okna - zazwyczaj informacja o wygranym
     */
    void endGame(String message);
    /**
     * Sprawdzanie czy widok został zamknięty przez użytkownika
     * @return
     */
    boolean isExited();
}
