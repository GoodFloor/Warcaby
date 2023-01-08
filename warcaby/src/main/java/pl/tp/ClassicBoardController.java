package pl.tp;

/**
 * Klasa obsługująca działania na planszy
 * 
 * Klasa konkretna we wzorcu Template method
 */
public class ClassicBoardController extends BoardController {

    @Override
    protected void resetBoard() {
        // board[0][0] to lewy dolny róg, pierwsza współrzędna to wiersz, druga to
        // kolumna
        Piece[][] temp = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) {
                    temp[i][j] = null;
                } else if (i < 3) {
                    temp[i][j] = new Piece();
                    temp[i][j].setColor("White");
                } else if (i > 4) {
                    temp[i][j] = new Piece();
                    temp[i][j].setColor("Red");
                } else {
                    temp[i][j] = null;
                }
            }
        }

        board.setHeight(8);
        board.setWidth(8);
        board.setPieces(temp);
        board.setMandatoryUsePieces(new int[0][0]);
        board.setNoWhiteRemaining(12);
        board.setNoRedRemaining(12);
        board.setWhiteTurn(true);
    }

    // TODO: Obsługa dam, wielokrotne bicia
    @Override
    protected boolean canKill(int posX, int posY) {
        Piece[][] tempBoard = board.getPieces();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int tempX = i * 4 - 2 + posX;
                int tempY = j * 4 - 2 + posY;
                if (tempX < 0 || tempX >= tempBoard.length || tempY < 0 || tempY >= tempBoard.length) {
                    continue;
                }
                if (tempBoard[tempY][tempX] != null) {
                    continue;
                }
                int[] killableEnemyPlace;
                try {
                    killableEnemyPlace = tempBoard[posY][posX].canGoTo(posX, posY, tempX, tempY);
                } catch (IncorrectPositionException e) {
                    continue;
                }
                int enemyX = killableEnemyPlace[0];
                int enemyY = killableEnemyPlace[1];
                if (tempBoard[enemyY][enemyX] != null
                        && tempBoard[enemyY][enemyX].getColor() != tempBoard[posY][posX].getColor()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void calculateMandatory() {
        Piece[][] tempBoard = board.getPieces();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tempBoard[i][j] != null
                        && ((board.isWhiteTurn() && tempBoard[i][j].getColor() == PieceColorEnum.White)
                                || (!board.isWhiteTurn() && tempBoard[i][j].getColor() == PieceColorEnum.Red))
                        && this.canKill(j, i)) {
                    this.addMandatory(j, i);
                }
            }
        }
    }
}
