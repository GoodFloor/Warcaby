package pl.tp;

public class Piece {
    private String color;
    private PieceState state;

    Piece() {
        this.state = new ClassicPawnState();
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        if(color == "Red")
            state.setBoardSide(8);
        else
            state.setBoardSide(0);
        this.color = color;
    }
    public int[] canGoTo(int posX1, int posY1, int posX2, int posY2) throws IncorrectPositionException {
        try {
            return state.canGo(posX1, posY1, posX2, posY2);
        } catch (IncorrectPositionException e) {
            throw e;
        }
    }

}
