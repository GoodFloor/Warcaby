package pl.tp.server;

/**
 * Klasa damy
 * 
 * Jeden ze stanów we wzorcu State
 */
public class EnglishQueenState extends AbstractPieceState {
    /**
     * Konstruktor ustawiający nazwę stanu
     */
    EnglishQueenState() {
        super();
    }

    @Override
    public int[][] canGoTo(int posX1, int posY1, int posX2, int posY2) throws IncorrectPositionException {
        if (Math.abs(posX2 - posX1) == Math.abs(posY2 - posY1)) {
            int[][] neededEnemyPosition = new int[Math.abs(posX2 - posX1) - 1][2];
            int tx = 1;
            int ty = 1;
            if (posX2 < posX1) {
                tx = -1;
            }
            if (posY2 < posY1) {
                ty = -1;
            }
            for (int i = 0; i < Math.abs(posX2 - posX1) - 1; i++) {
                neededEnemyPosition[i][0] = posX1 + (i + 1) * tx;
                neededEnemyPosition[i][1] = posY1 + (i + 1) * ty;
            }
            return neededEnemyPosition;
        }
        throw new IncorrectPositionException();
    }

}
