package pl.tp;

/**
 * Opisywanie stanu pionka (np. zwykły/dama)
 */
public abstract class PieceState {
    protected int shift;
    public abstract int[] canGo(int posX1, int posY1, int posX2, int posY2) throws IncorrectPositionException;
    
    public void setShift(int s) {
        this.shift = s;
    }
    protected String codePosition(int posX, int posY) {
        String result = "";
        result += (char) (posX + 65);
        result += posY + 1;
        return result;
    }
}
