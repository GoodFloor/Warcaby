package pl.tp.server;

import pl.tp.PieceColorEnum;
import pl.tp.PieceStateEnum;

/**
 * Klasa obsługująca działania na planszy
 * 
 * Klasa konkretna we wzorcu Template method
 */
public class PolishBoardController extends BoardController {

    @Override
    protected void resetBoard() {
        // board[0][0] to lewy dolny róg, pierwsza współrzędna to wiersz, druga to
        // kolumna
        AbstractPiece[][] temp = new PolishPiece[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // if ((i + j) % 2 == 1) {
                // temp[i][j] = null;
                // } else if (i < 4) {
                // temp[i][j] = new PolishPiece();
                // temp[i][j].setColor(PieceColorEnum.White);
                // } else if (i > 5) {
                // temp[i][j] = new PolishPiece();
                // temp[i][j].setColor(PieceColorEnum.Red);
                // } else {
                // temp[i][j] = null;
                // }
                temp[i][j] = null;
            }
        }
        temp[0][0] = new PolishPiece();
        temp[0][0].setColor(PieceColorEnum.White);
        temp[0][4] = new PolishPiece();
        temp[0][4].setColor(PieceColorEnum.White);
        temp[1][1] = new PolishPiece();
        temp[1][1].setColor(PieceColorEnum.White);
        temp[1][3] = new PolishPiece();
        temp[1][3].setColor(PieceColorEnum.White);
        temp[8][0] = new PolishPiece();
        temp[8][0].setColor(PieceColorEnum.White);
        temp[3][3] = new PolishPiece();
        temp[3][3].setColor(PieceColorEnum.White);

        temp[7][7] = new PolishPiece();
        temp[7][7].setColor(PieceColorEnum.Red);
        temp[7][5] = new PolishPiece();
        temp[7][5].setColor(PieceColorEnum.Red);
        temp[7][3] = new PolishPiece();
        temp[7][3].setColor(PieceColorEnum.Red);
        temp[6][2] = new PolishPiece();
        temp[6][2].setColor(PieceColorEnum.Red);
        temp[6][4] = new PolishPiece();
        temp[6][4].setColor(PieceColorEnum.Red);
        temp[3][7] = new PolishPiece();
        temp[3][7].setColor(PieceColorEnum.Red);
        temp[4][2] = new PolishPiece();
        temp[4][2].setColor(PieceColorEnum.Red);

        board.setHeight(10);
        board.setWidth(10);
        board.setPieces(temp);
        board.setMandatoryUsePieces(new int[0][0]);
        board.setNoWhiteRemaining(20);
        board.setNoRedRemaining(20);
        board.setWhiteTurn(true);
    }

    @Override
    protected boolean canKill(int posX, int posY) {
        AbstractPiece[][] tempBoard = board.getPieces();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // jeśli na sprawdzanym miejscu stoi pionek
                if (tempBoard[j][i] != null) {
                    continue;
                }
                int[][] killableEnemyPlace;
                try {
                    killableEnemyPlace = tempBoard[posY][posX].canGoTo(posX, posY, i, j);
                } catch (IncorrectPositionException e) {
                    continue;
                } catch (Exception e) {
                    continue;
                }

                boolean blocked = false;
                int enemiesCount = 0;
                for (int[] possibleEnemy : killableEnemyPlace) {
                    int possibleEnemyX = possibleEnemy[0];
                    int possibleEnemyY = possibleEnemy[1];
                    // pionek sojusznika pomiędzy
                    if (tempBoard[possibleEnemyY][possibleEnemyX] != null
                            && tempBoard[possibleEnemyY][possibleEnemyX].getColor() == tempBoard[posY][posX]
                                    .getColor()) {
                        blocked = true;
                        break;
                    }
                    if (tempBoard[possibleEnemyY][possibleEnemyX] != null
                            && tempBoard[possibleEnemyY][possibleEnemyX].getColor() != tempBoard[posY][posX]
                                    .getColor()) {
                        enemiesCount++;
                    }
                }
                if (blocked) {
                    continue;
                }
                if (enemiesCount == 1) {
                    return true;
                } else if ((enemiesCount == 0 && tempBoard[posY][posX].getStateName() == PieceStateEnum.Pawn
                        && killableEnemyPlace.length > 0)
                        || enemiesCount > 1) {
                    continue;
                }
            }
        }
        return false;
    }

    @Override
    protected void calculateMandatory() {
        AbstractPiece[][] tempBoard = board.getPieces();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tempBoard[i][j] != null
                        && ((board.isWhiteTurn() && tempBoard[i][j].getColor() == PieceColorEnum.White)
                                || (!board.isWhiteTurn() && tempBoard[i][j].getColor() == PieceColorEnum.Red))
                        && this.canKill(j, i)) {
                    this.addMandatory(j, i);
                    System.out.println("Mandatory: " + i + "; " + j);
                }
            }
        }
    }

    @Override
    protected boolean canBecomeQueen() {
        if (board.isTurnOver()) {
            return true;
        }
        return false;
    }
}
