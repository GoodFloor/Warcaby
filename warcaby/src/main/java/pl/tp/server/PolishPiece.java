package pl.tp.server;

import pl.tp.PieceStateEnum;

/**
 * Klasa przechowująca informacje o pionku w angielskiej wersji gry
 * 
 * Twórca konkretny we wzorcy Abstract Factory
 */
public class PolishPiece extends AbstractPiece {

    /**
     * Konstruktor ustawiający rodzaj pionka na zwykły
     */
    PolishPiece() {
        state = new PolishPawnState();
    }

    @Override
    public void upgradePiece() {

        if (state.getState() == PieceStateEnum.Pawn) {
            state = new PolishQueenState();
        }

    }

}
