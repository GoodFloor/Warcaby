package pl.tp.server;

/**
 * Klasa przechowująca model planszy
 * 
 * Model we wzorcu MVC
 */
public class Board {
    private int width;
    private int height;
    private AbstractPiece[][] pieces;
    private int noWhiteRemaining;
    private int noRedRemaining;
    private boolean isTurnOver;
    private boolean whiteTurn;
    private int[][] mandatoryUsePieces;
    private int bestMoveLength;

    /**
     * Pobieranie informacji o długości najlepszego ruchu
     * 
     * @return Zwraca długość najlepszego ruchu
     */
    public int getBestMoveLength() {
        return bestMoveLength;
    }

    /**
     * Ustawianie długości najlepszego ruchu
     * 
     * @param bestMoveLength długość najlepszego ruchu
     */
    public void setBestMoveLength(int bestMoveLength) {
        this.bestMoveLength = bestMoveLength;
    }

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
    public AbstractPiece[][] getPieces() {
        return pieces;
    }

    /**
     * Ustawianie układu pionków na planszy
     * 
     * @param board tablica dwuwymiarowa z pionkami
     */
    public void setPieces(AbstractPiece[][] board) {
        this.pieces = board;
    }

    /**
     * Pobieranie liczby pozostałych białych pionków z planszy
     * 
     * @return Zwraca liczbę pionków
     */
    public int getNoWhiteRemaining() {
        return noWhiteRemaining;
    }

    /**
     * Ustawianie liczby białych pionków na planszy
     * 
     * @param noWhiteRemaining liczba pionków
     */
    public void setNoWhiteRemaining(int noWhiteRemaining) {
        this.noWhiteRemaining = noWhiteRemaining;
    }

    /**
     * Pobieranie liczby pozostałych czerwonych pionków z planszy
     * 
     * @return Zwraca liczbę pionków
     */
    public int getNoRedRemaining() {
        return noRedRemaining;
    }

    /**
     * Ustawianie liczby czerwonych pionków na planszy
     * 
     * @param noRedRemaining liczba pionków
     */
    public void setNoRedRemaining(int noRedRemaining) {
        this.noRedRemaining = noRedRemaining;
    }

    /**
     * Pobieranie informacji czy tura się zakończyła
     * 
     * @return Zwraca prawdę, jeśli tura się skończyła.
     */
    public boolean isTurnOver() {
        return isTurnOver;
    }

    /**
     * Ustawianie informacji o końcu tury
     * 
     * @param isTurnOver prawda, jeśli tura się skończyła
     */
    public void setTurnOver(boolean isTurnOver) {
        this.isTurnOver = isTurnOver;
    }

    /**
     * Pobieranie informacji czy jest to tura białych czy czerwonych
     * 
     * @return Zwraca prawdę, jeśli obecnie jest tura białych.
     */
    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    /**
     * Ustawianie czyja jest obecna tura
     * 
     * @param whiteTurn prawda, jeśli jest tura białych
     */
    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    /**
     * Pobieranie listy pionków, którymi trzeba bić
     * 
     * @return Zwraca tablicę dwuwymiarową z pionkami.
     */
    public int[][] getMandatoryUsePieces() {
        return mandatoryUsePieces;
    }

    /**
     * Ustawianie listy pionków, którymi trzeba bić
     * 
     * @param mandatoryUsePieces tablica dwuwymiarowa z listą pionków
     */
    public void setMandatoryUsePieces(int[][] mandatoryUsePieces) {
        this.mandatoryUsePieces = mandatoryUsePieces;
    }
}
