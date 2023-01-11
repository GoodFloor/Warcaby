package pl.tp.server;

import pl.tp.PieceColorEnum;
import pl.tp.PieceStateEnum;

/**
 * Klasa przechowująca informacje o pionku
 * 
 * Twórca abstrakcyjny we wzorcu Abstract Factory
 */
public abstract class AbstractPiece {
    private PieceColorEnum color;
    protected AbstractPieceState state;

    /**
     * Zmiana stanu pionka ze zwykłego pionka na damę
     */
    public abstract void upgradePiece();

    /**
     * Pobieranie informacji o kolorze pionka
     * 
     * @return kolor pionka
     */
    public PieceColorEnum getColor() {
        return color;
    }

    /**
     * Ustawianie informacji o kolorze pionka
     * 
     * @param color kolor pionka
     */
    public void setColor(PieceColorEnum color) {
        this.color = color;
        if (color.equals(PieceColorEnum.Red)) {
            state.setStartingAtBottom(false);
        } else {
            state.setStartingAtBottom(true);
        }
    }

    /**
     * Pobieranie informacji czy dany ruch jest możliwy
     * 
     * @param posX1 początkowa pozycja x
     * @param posY1 początkowa pozycja y
     * @param posX2 docelowa pozycja x
     * @param posY2 docelowa pozycja y
     * 
     * @return Zwraca pustą tablicę jeżeli ruch jest możliwy bez dodatkowych
     *         warunków lub tablicę z możliwymi pozycjami przeciwnika którego trzeba
     *         zbić aby
     *         móc się poruszyć
     * @throws IncorrectPositionException Zwraca błąd w przypadku podania
     *                                    niepoprawnych pozycji
     */
    public int[][] canGoTo(int posX1, int posY1, int posX2, int posY2) throws IncorrectPositionException {
        try {
            return state.canGoTo(posX1, posY1, posX2, posY2);
        } catch (IncorrectPositionException e) {
            throw e;
        }
    }

    /**
     * Pobranie informacji o nazwie stanu
     * 
     * @return nazwa stanu
     */
    public PieceStateEnum getStateName() {
        return state.getState();
    }

    /**
     * Pobranie informacji o stronie z której rozpoczyna grę pionek
     * 
     * @return prawda, jeśli zaczyna na dole planszy
     */
    public boolean isStartingAtBottom() {
        return state.getIsStartingAtBottom();
    }

}
