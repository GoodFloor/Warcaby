package pl.tp;

/**
 * Opisywanie stanu pionka (np. zwykły/dama)
 */
public abstract class PieceState {
    protected int shift;
    public abstract String canGo(String position1, String position2);
    
    public void setShift(int s) {
        this.shift = s;
    }
    protected int decodePositionX(String position) {
        return (int) position.charAt(0) - 65;
    }
    protected int decodePositionY(String position) {
        return Integer.parseInt(position.substring(1, position.length())) - 1;
    }
    protected String codePosition(int posX, int posY) {
        String result = "";
        result += (char) (posX + 65);
        result += posY + 1;
        return result;
    }
}
