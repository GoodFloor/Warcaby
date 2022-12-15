package pl.tp;

public class Piece {
    private String color;
    private PieceState state;

    Piece() {
        this.state = new ClassicPawnState();
    }
    public String getType() {
        return color;
    }
    public void setType(String type) {
        if(type == "Red")
            state.setShift(8);
        else
            state.setShift(0);
        this.color = type;
    }
    public int[] canGoTo(int posX1, int posY1, int posX2, int posY2) {
        return state.canGo(posX1, posY1, posX2, posY2);
    }

}
