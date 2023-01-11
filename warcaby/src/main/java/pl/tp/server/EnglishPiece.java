package pl.tp.server;

import pl.tp.PieceStateEnum;

public class EnglishPiece extends AbstractPiece {

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
