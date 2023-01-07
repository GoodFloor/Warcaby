package pl.tp;

/**
 * Klasa przechowująca model planszy
 * 
 * Model we wzorcu MVC
 */
public class Board {
    private int width;
    private int height;
    private Piece[][] pieces;
    private int noWhiteRemaining;
    private int noRedRemaining;

    /**
     * Pobieranie informacji o szerokości planszy
     * 
     * @return Zwraca liczbę pól na planszy w poziomie
     */
    public int getWidth() {
        return width;
    }

    /**
     * Ustawianie szerokości planszy
     * 
     * @param width liczba pól na planszy w poziomie
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Pobieranie informacji o wysokości planszy
     * 
     * @return Zwraca liczbę pól na planszy w pionie
     */
    public int getHeight() {
        return height;
    }

    /**
     * Ustawianie wysokości planszy
     * 
     * @param height liczba pól na planszy w pionie
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Pobieranie układu pionków na planszy
     * 
     * @return Zwraca dwuwymiarową tablicę z pionkami.
     */
    public Piece[][] getPieces() {
        return pieces;
    }

    /**
     * Ustawianie układu pionków na planszy
     * 
     * @param board tablica dwuwymiarowa z pionkami
     */
    public void setPieces(Piece[][] board) {
        this.pieces = board;
    }

    public int getNoWhiteRemaining() {
        return noWhiteRemaining;
    }

    public void setNoWhiteRemaining(int noWhiteRemaining) {
        this.noWhiteRemaining = noWhiteRemaining;
    }

    public int getNoRedRemaining() {
        return noRedRemaining;
    }

    public void setNoRedRemaining(int noRedRemaining) {
        this.noRedRemaining = noRedRemaining;
    }

}
