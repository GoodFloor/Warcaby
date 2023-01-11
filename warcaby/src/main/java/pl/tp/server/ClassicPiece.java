package pl.tp.server;

import pl.tp.PieceStateEnum;

/**
 * Klasa przechowująca informacje o pionku w klasycznej wersji gry
 * 
 * Twórca konkretny we wzorcy Abstract Factory
 */
public class ClassicPiece extends AbstractPiece {

    /**
     * Konstruktor ustawiający rodzaj pionka na zwykły
     */
    ClassicPiece() {
        state = new ClassicPawnState();
    }

    @Override
    public void upgradePiece() {

        if (state.getState() == PieceStateEnum.Pawn) {
            state = new ClassicQueenState();
        }

    }

}
