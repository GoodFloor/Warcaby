package pl.tp.server;

/**
 * Klasa podstawowego pionka
 * 
 * Jeden ze stanów we wzorcu State
 */
public class EnglishPawnState extends PawnState {

    /**
     * Konstruktor ustalający nazwę stanu
     */
    EnglishPawnState() {
        super();
    }

    @Override
    public int[][] canGoTo(int posX1, int posY1, int posX2, int posY2) throws IncorrectPositionException {
        int shifter;
        if (isStartingAtBottom) {
            shifter = 1;
        } else {
            shifter = -1;
        }
        if (Math.abs(posX2 - posX1) == 1 && shifter * (posY2 - posY1) == 1) {
            return new int[0][0];
        } else if (Math.abs(posX2 - posX1) == 2 && Math.abs(posY2 - posY1) == 2) {
            int[][] neededEnemyPosition = new int[1][2];
            neededEnemyPosition[0][0] = (posX2 + posX1) / 2;
            neededEnemyPosition[0][1] = (posY2 + posY1) / 2;
            return neededEnemyPosition;
        }
        throw new IncorrectPositionException();
    }

}
