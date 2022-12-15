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

}
