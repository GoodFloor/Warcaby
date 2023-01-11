package pl.tp.server;

import pl.tp.PieceStateEnum;

/**
 * Klasa podstawowego pionka
 * 
 * Produkt abstrakcyjny 2 we wzorcu Abstract Factory
 */
public abstract class AbstractPawnState extends AbstractPieceState {

    /**
     * Konstruktor ustalający nazwę stanu
     */
    AbstractPawnState() {
        stateName = PieceStateEnum.Pawn;
    }

    public abstract int[][] canGoTo(int posX1, int posY1, int posX2, int posY2) throws IncorrectPositionException;
}
