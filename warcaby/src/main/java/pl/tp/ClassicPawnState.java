package pl.tp;

public class ClassicPawnState extends PieceState {

    @Override
    public String canGo(String position1, String position2) {
        int posX1 = this.decodePositionX(position1);
        int posY1 = this.decodePositionY(position1);
        int posX2 = this.decodePositionX(position2);
        int posY2 = this.decodePositionY(position2);
        String result = "";

        // posX > 1 && posY < 6 && tempBoard[posX - 1][posY - 1].getClass() == Piece.class
        //         && tempBoard[posX][posY].getType() != tempBoard[posX - 1][posY - 1].getType()
        //         && tempBoard[posX - 2][posY - 2] == null
        return null;
    }
    
}
