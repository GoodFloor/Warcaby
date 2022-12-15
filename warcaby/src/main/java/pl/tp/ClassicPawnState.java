package pl.tp;

public class ClassicPawnState extends PieceState {

    @Override
    public int[] canGo(int posX1, int posY1, int posX2, int posY2) throws IncorrectPositionException {
        int shifter;
        if(boardSide == 0)
            shifter = 1;
        else 
            shifter = -1;
        if(Math.abs(posX2 - posX1) == 1 && shifter * (posY2 - posY1) == 1)
            return new int[0];
        else if(Math.abs(posX2 - posX1) == 2 && Math.abs(posY2 - posY1) == 2) {
            int[] neededEnemyPosition = new int[2];
            neededEnemyPosition[0] = (posX2 + posX1) / 2;
            neededEnemyPosition[1] = (posY2 + posY1) / 2;
            return neededEnemyPosition;
        }
        throw new IncorrectPositionException();
    }
    
}
