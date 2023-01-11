package pl.tp.server;

import pl.tp.PieceStateEnum;

/**
 * Klasa przechowująca informacje o pionku
 * 
 * Twórca konkretny we wzorcy Factory
 */
public class ClassicPiece extends AbstractPiece {

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
