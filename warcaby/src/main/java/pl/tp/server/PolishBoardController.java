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
                if ((i + j) % 2 == 1) {
                    temp[i][j] = null;
                } else if (i < 4) {
                    temp[i][j] = new PolishPiece();
                    temp[i][j].setColor(PieceColorEnum.White);
                } else if (i > 5) {
                    temp[i][j] = new PolishPiece();
                    temp[i][j].setColor(PieceColorEnum.Red);
                } else {
                    temp[i][j] = null;
                }
                // temp[i][j] = null;
            }
        }
        // temp[0][0] = new PolishPiece();
        // temp[0][0].setColor(PieceColorEnum.White);
        // temp[0][4] = new PolishPiece();
        // temp[0][4].setColor(PieceColorEnum.White);
        // temp[1][1] = new PolishPiece();
        // temp[1][1].setColor(PieceColorEnum.White);
        // temp[1][3] = new PolishPiece();
        // temp[1][3].setColor(PieceColorEnum.White);
        // temp[8][0] = new PolishPiece();
        // temp[8][0].setColor(PieceColorEnum.White);
        // temp[3][3] = new PolishPiece();
        // temp[3][3].setColor(PieceColorEnum.White);

        // temp[7][7] = new PolishPiece();
        // temp[7][7].setColor(PieceColorEnum.Red);
        // temp[7][5] = new PolishPiece();
        // temp[7][5].setColor(PieceColorEnum.Red);
        // temp[7][3] = new PolishPiece();
        // temp[7][3].setColor(PieceColorEnum.Red);
        // temp[6][2] = new PolishPiece();
        // temp[6][2].setColor(PieceColorEnum.Red);
        // temp[6][4] = new PolishPiece();
        // temp[6][4].setColor(PieceColorEnum.Red);
        // temp[3][7] = new PolishPiece();
        // temp[3][7].setColor(PieceColorEnum.Red);
        // // temp[4][2] = new PolishPiece();
        // // temp[4][2].setColor(PieceColorEnum.Red);
        // temp[2][4] = new PolishPiece();
        // temp[2][4].setColor(PieceColorEnum.Red);

        board.setHeight(10);
        board.setWidth(10);
        board.setPieces(temp);
        board.setMandatoryUsePieces(new int[0][0]);
        board.setNoWhiteRemaining(20);
        board.setNoRedRemaining(20);
        board.setWhiteTurn(true);

        board.setBestMoveLength(0);
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
    protected int calculateBestMandatory(AbstractPiece[][] boardState, int posX, int posY, int size) {
        System.out.println(size + ". " + posX + " " + posY);
        int maxSize = size;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    // Sprawdzamy czy miejsce docelowe nie jest zajęte
                    if (boardState[j][i] != null) {
                        continue;
                    }

                    if ((i + j) % 2 == 1) {
                        continue;
                    }

                    // Sprawdzamy czy podany ruch jest możliwy i czy aby go wykonać musimy zbić
                    // przeciwnika
                    int[][] neededEnemyPosition;
                    // System.out.println(i + " " + j);
                    // System.out.println(boardState[posY][posX]);

                    neededEnemyPosition = boardState[posX][posY].canGoTo(posX, posY, j, i);

                    // Jeżeli przeskakujemy o więcej niż 1 pole to sprawdzamy czy pomiędzy nimi jest
                    // przeciwnik, jeśli tak to go usuwamy
                    int enemiesCount = 0;
                    int enemyX = 0;
                    int enemyY = 0;
                    for (int[] possibleEnemy : neededEnemyPosition) {
                        int possibleEnemyX = possibleEnemy[1];
                        int possibleEnemyY = possibleEnemy[0];
                        // System.out.println("X: " + possibleEnemyX);
                        // System.out.println("Y: " + possibleEnemyY);

                        if (boardState[possibleEnemyY][possibleEnemyX] != null) {
                            // System.out.println("Jest pionek w odpowiednim miejscu");
                            if (boardState[possibleEnemyY][possibleEnemyX].getColor() == boardState[posX][posY]
                                    .getColor()) {
                                continue;
                            }
                            enemiesCount++;
                            enemyX = possibleEnemyX;
                            enemyY = possibleEnemyY;
                        }
                    }
                    if (enemiesCount == 0 || enemiesCount > 1) {
                        // System.out.println("Enemies count: " + enemiesCount);
                        continue;
                    } else if (enemiesCount == 1) {
                        // System.out.println("Enemies count: " + enemiesCount);

                        AbstractPiece deleted = boardState[enemyY][enemyX];
                        boardState[enemyY][enemyX] = null;
                        boardState[j][i] = boardState[posX][posY];
                        boardState[posX][posY] = null;

                        int innerSize = calculateBestMandatory(boardState, j, i, size + 1);

                        if (innerSize > maxSize) {
                            maxSize = innerSize;
                        }
                        boardState[posX][posY] = boardState[j][i];
                        boardState[j][i] = null;
                        boardState[enemyY][enemyX] = deleted;

                    }
                } catch (IncorrectPositionException e) {
                    continue;
                }
            }
        }
        // System.out.println("ZAKOŃCZONO");
        return maxSize;

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

        int maxSize = 0;
        int[] sizes = new int[board.getMandatoryUsePieces().length];
        for (int i = 0; i < board.getMandatoryUsePieces().length; i++) {
            int[] piece = board.getMandatoryUsePieces()[i];

            int posX = piece[0];
            int posY = piece[1];
            int s = calculateBestMandatory(tempBoard, posX, posY, 0);

            if (maxSize < s) {
                maxSize = s;
            }
            sizes[i] = s;
        }

        board.setBestMoveLength(maxSize);

        // int count = 0;
        // for (int i = 0; i < sizes.length; i++) {
        // if (sizes[i] == maxSize) {
        // count++;
        // }
        // }

        // int[][] temp = new int[count][2];
        // int j = 0;
        // for (int i = 0; i < board.getMandatoryUsePieces().length; i++) {
        // if (sizes[i] == maxSize) {
        // temp[j][0] = board.getMandatoryUsePieces()[i][0];
        // temp[j][1] = board.getMandatoryUsePieces()[i][1];
        // j++;
        // }
        // }

        // board.setMandatoryUsePieces(temp);

        System.out.println(maxSize);

    }

    @Override
    protected boolean canBecomeQueen() {
        if (board.isTurnOver()) {
            return true;
        }
        return false;
    }
}
