package pl.tp.server;

import pl.tp.PieceStateEnum;

/**
 * Klasa przechowująca informacje o pionku w angielskiej wersji gry
 * 
 * Twórca konkretny we wzorcy Abstract Factory
 */
public class EnglishPiece extends AbstractPiece {

    /**
     * Konstruktor ustawiający rodzaj pionka na zwykły
     */
    EnglishPiece() {
        state = new EnglishPawnState();
    }

    @Override
    public void upgradePiece() {

        if (state.getState() == PieceStateEnum.Pawn) {
            state = new EnglishQueenState();
        }

    }

}
