package pl.tp;

/**
 * Klasa obsługująca działania na planszy
 * 
 * Klasa konkretna we wzorcu Template method
 */
public class ClassicBoardController extends BoardController {

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

        board.setPieces(temp);
        board.setMandatoryUsePieces(new int[0][0]);
        board.setNoWhiteRemaining(12);
        board.setNoRedRemaining(12);
        board.setWhiteTurn(true);
    }

    // TODO: Obsługa dam
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

    public SquareStateEnum[][] translateBoard() {
        SquareStateEnum[][] result = new SquareStateEnum[8][8];
        Piece[][] boardContent = board.getPieces();

        int h = board.getHeight();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if ((i + j) % 2 == 1) {
                    result[h - i][j] = SquareStateEnum.White;
                } else if (boardContent[i][j] == null) {
                    result[h - i][j] = SquareStateEnum.BlackEmpty;
                } else if (boardContent[i][j].getColor() == "White") {
                    result[h - i][j] = SquareStateEnum.BlackWhite;
                } else if (boardContent[i][j].getColor() == "Red") {
                    result[h - i][j] = SquareStateEnum.BlackRed;
                }
            }
        }

        return result;
    }

    private void addMandatory(int posX, int posY) {

        int[][] mandatoryArr = board.getMandatoryUsePieces();
        int[][] tempArr = new int[mandatoryArr.length + 1][2];

        for (int i = 0; i < mandatoryArr.length; i++) {
            tempArr[i] = mandatoryArr[i];
        }

        tempArr[mandatoryArr.length][0] = posX;
        tempArr[mandatoryArr.length][1] = posY;

        board.setMandatoryUsePieces(tempArr);
    }

    @Override
    public void startNextTurn() {
        isTurnOver = false;
        board.setMandatoryUsePieces(new int[0][0]);
        board.setWhiteTurn(!board.isWhiteTurn());
        Piece[][] tempBoard = board.getPieces();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tempBoard[i][j] != null && ((board.isWhiteTurn() && tempBoard[i][j].getColor() == "White")
                        || (!board.isWhiteTurn() && tempBoard[i][j].getColor() == "Red")) && this.canKill(j, i)) {
                    this.addMandatory(j, i);
                }
            }
        }
    }

    @Override
    public String isGameOver() {
        if (board.getNoWhiteRemaining() == 0) {
            return "White win!";
        } else if (board.getNoRedRemaining() == 0) {
            return "Red win!";
        } else {
            return null;
        }
    }
}
