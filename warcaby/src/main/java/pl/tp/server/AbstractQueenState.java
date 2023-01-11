package pl.tp.server;

import pl.tp.PieceStateEnum;

/**
 * Klasa damy
 * 
 * Produkt abstrakcyjny 1 we wzorcu Abstract Factory
 */
public abstract class AbstractQueenState extends AbstractPieceState {
    /**
     * Konstruktor ustawiający nazwę stanu
     */
    AbstractQueenState() {
        stateName = PieceStateEnum.Queen;
    }

    public abstract int[][] canGoTo(int posX1, int posY1, int posX2, int posY2) throws IncorrectPositionException;

}
