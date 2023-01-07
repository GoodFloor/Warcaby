package pl.tp;

/**
 * Klasa obsługująca działania na planszy
 * 
 * Klasa abstrakcyjna we wzorcu Template method
 * Kontroler we wzorcu MVC
 */
public abstract class BoardController {
    protected Board board;
    protected boolean isTurnOver;

    protected abstract void resetBoard();

    protected abstract boolean canKill(int posX, int posY);

    public abstract void startNextTurn();

    public abstract String isGameOver();

    public abstract SquareStateEnum[][] translateBoard();

    public BoardController() {
        board = new Board();
        isTurnOver = false;
        resetBoard();
    }

    public boolean isTurnOver() {
        return isTurnOver;
    }

    protected int decodePositionX(String position) throws IncorrectPositionException {
        try {
            return (int) position.charAt(0) - 65;
        } catch (Exception e) {
            throw new IncorrectPositionException();
        }
    }

    protected int decodePositionY(String position) throws IncorrectPositionException {
        try {
            return Integer.parseInt(position.substring(1, position.length())) - 1;
        } catch (Exception e) {
            throw new IncorrectPositionException();
        }
    }

    protected String codePosition(int posX, int posY) {
        String result = "";
        result += (char) (posX + 65);
        result += posY + 1;
        return result;
    }

    public void movePiece(String pos1, String pos2) throws IncorrectPositionException {

        try {
            // Konwertujemy miejsce źródłowe pionka i miejsce docelowe pionka
            int posX1 = this.decodePositionX(pos1);
            int posY1 = this.decodePositionY(pos1);
            int posX2 = this.decodePositionX(pos2);
            int posY2 = this.decodePositionY(pos2);

            // Pobieramy zawartość planszy
            Piece[][] tempBoard = board.getPieces();

            // Sprawdzamy czy na miejscu źródłowym jest pionek i czy miejsce docelowe jest
            // puste
            if (tempBoard[posY1][posX1] == null || tempBoard[posY2][posX2] != null) {
                throw new IncorrectPositionException();
            }
            // Sprawdzamy czy pionek źródłowy jest w dobrym kolorze
            if ((board.isWhiteTurn() && tempBoard[posY1][posX1].getColor() != "White")
                    || (!board.isWhiteTurn() && tempBoard[posY1][posX1].getColor() != "Red")) {
                throw new IncorrectPositionException();
            }
            // Sprawdzamy czy podany pionek jest pośród pionków którymi trzeba się ruszyć
            if (board.getMandatoryUsePieces().length > 0) {
                boolean isPieceInMandatory = false;
                for (int[] mandatoryPiece : board.getMandatoryUsePieces()) {
                    if (mandatoryPiece[0] == posX1 && mandatoryPiece[1] == posY1 && Math.abs(posX1 - posX2) == 2
                            && Math.abs(posY1 - posY2) == 2) {
                        isPieceInMandatory = true;
                        break;
                    }
                }
                if (!isPieceInMandatory) {
                    throw new IncorrectPositionException();
                }
            }
            // Sprawdzamy czy podany ruch jest możliwy i czy aby go wykonać musimy zbić
            // przeciwnika
            int[] neededEnemyPosition;
            neededEnemyPosition = tempBoard[posY1][posX1].canGoTo(posX1, posY1, posX2, posY2);

            // Jeżeli przeskakujemy o 2 pola to sprawdzamy czy pomiędzy nimi jest
            // przeciwnik, jeśli tak to go usuwamy
            if (neededEnemyPosition.length > 0) {
                int enemyX = neededEnemyPosition[0];
                int enemyY = neededEnemyPosition[1];
                if (tempBoard[enemyY][enemyX] == null
                        || tempBoard[enemyY][enemyX].getColor() == tempBoard[posY1][posX1].getColor()) {
                    throw new IncorrectPositionException();
                } else {
                    if (tempBoard[enemyY][enemyX].getColor() == "Red")
                        board.setNoRedRemaining(board.getNoRedRemaining() - 1);
                    else
                        board.setNoWhiteRemaining(board.getNoWhiteRemaining() - 1);
                    tempBoard[enemyY][enemyX] = null;
                }
            }
            // Przesuwamy pionek
            tempBoard[posY2][posX2] = tempBoard[posY1][posX1];
            tempBoard[posY1][posX1] = null;
            board.setMandatoryUsePieces(new int[0][0]);
            board.setPieces(tempBoard);
            isTurnOver = true;

            // Jeśli zbiliśmy pionka to sprawdzamy czy ten pionek może wykonać jeszcze jakiś
            // ruch
            if (neededEnemyPosition.length > 0) {
                if (this.canKill(posX2, posY2)) {
                    int[][] temp = new int[1][2];
                    temp[0][0] = posX2;
                    temp[0][1] = posY2;

                    board.setMandatoryUsePieces(temp);
                }
            }
        } catch (IncorrectPositionException e) {
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncorrectPositionException();
        }

    }

}
