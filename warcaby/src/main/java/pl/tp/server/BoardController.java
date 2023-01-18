package pl.tp.server;

import pl.tp.PieceColorEnum;
import pl.tp.PieceStateEnum;
import pl.tp.SquareStateEnum;

/**
 * Klasa obsługująca działania na planszy
 * 
 * Klasa abstrakcyjna we wzorcu Template method
 * Kontroler we wzorcu MVC
 */
public abstract class BoardController {
    protected Board board;

    /**
     * Ustawienie początkowych parametrów planszy
     */
    protected abstract void resetBoard();

    /**
     * Sprawdzenie czy dany pionek ma możliwość jakiegokolwiek bicia
     * 
     * @param posX pozycja x pionka do sprawdzenia
     * @param posY pozycja x pionka do sprawdzenia
     * @return Zwraca prawdę, jeśli pionek ma możliwość bicia.
     */
    protected abstract boolean canKill(int posX, int posY);

    /**
     * Aktualizowanie listy pionków, które mają obowiązek bicia
     */
    protected abstract void calculateMandatory();

    /**
     * Sprawdzanie, czy pionek może zostać damką w tym momencie tury
     * 
     * @return Zwraca prawdę, jeśli może zostać damką.
     */
    protected abstract boolean canBecomeQueen();

    /**
     * Konstruktor ustawiający początkowe parametry i resetujący planszę
     */
    public BoardController() {
        board = new Board();
        board.setTurnOver(false);
        resetBoard();
    }

    /**
     * Informacja o końcu tury
     * 
     * @return Zwraca prawdę, jeśli tura się zakończyła.
     */
    public boolean isTurnOver() {
        return board.isTurnOver();
    }

    /**
     * Wyciąganie informacji o pozycji x pionka z odebranego stringa
     * 
     * @param position informacja do rozkodowania
     * @return numer pozycji x
     * @throws IncorrectPositionException podano błędną pozycję
     */
    protected int decodePositionX(String position) throws IncorrectPositionException {
        try {
            return (int) position.charAt(0) - 65;
        } catch (Exception e) {
            System.out.println("Niezgodny X");
            throw new IncorrectPositionException();
        }
    }

    /**
     * Wyciąganie informacji o pozycji y pionka z odebranego stringa
     * 
     * @param position informacja do rozkodowania
     * @return numer pozycji y
     * @throws IncorrectPositionException podano błędną pozycję
     */
    protected int decodePositionY(String position) throws IncorrectPositionException {
        try {
            return Integer.parseInt(position.substring(1, position.length())) - 1;
        } catch (Exception e) {
            System.out.println("Niezgodny Y");
            throw new IncorrectPositionException();
        }
    }

    /**
     * Przesunięcie pionka na planszy
     * 
     * @param pos1 pozycja początkowa
     * @param pos2 pozycja docelowa
     * @throws IncorrectPositionException któraś z podanych pozycji jest błędna
     */
    public void movePiece(String pos1, String pos2) throws IncorrectPositionException {

        try {
            // Konwertujemy miejsce źródłowe pionka i miejsce docelowe pionka
            int posX1 = this.decodePositionX(pos1);
            int posY1 = this.decodePositionY(pos1);
            int posX2 = this.decodePositionX(pos2);
            int posY2 = this.decodePositionY(pos2);

            // Pobieramy zawartość planszy
            AbstractPiece[][] tempBoard = board.getPieces();

            // Sprawdzamy czy na miejscu źródłowym jest pionek i czy miejsce docelowe jest
            // puste
            if (tempBoard[posY1][posX1] == null || tempBoard[posY2][posX2] != null) {
                System.out.println("Miejsce źródłowe puste lub docelowe zajęte");
                throw new IncorrectPositionException();
            }
            // Sprawdzamy czy pionek źródłowy jest w dobrym kolorze
            if ((board.isWhiteTurn() && tempBoard[posY1][posX1].getColor() != PieceColorEnum.White)
                    || (!board.isWhiteTurn() && tempBoard[posY1][posX1].getColor() != PieceColorEnum.Red)) {
                System.out.println("Wybrany pionek nie należy do gracza");
                throw new IncorrectPositionException();
            }
            // Sprawdzamy czy podany pionek jest pośród pionków którymi trzeba się ruszyć
            boolean isPieceInMandatory = false;
            if (board.getMandatoryUsePieces().length > 0) {
                for (int[] mandatoryPiece : board.getMandatoryUsePieces()) {
                    if (mandatoryPiece[0] == posY1 && mandatoryPiece[1] == posX1) {
                        isPieceInMandatory = true;
                        break;
                    }
                }
                if (!isPieceInMandatory) {
                    String mandatory = "";
                    for (int[] mandatoryPiece : board.getMandatoryUsePieces()) {
                        mandatory += "(" + mandatoryPiece[0] + "; " + mandatoryPiece[1] + "), ";
                    }
                    System.out.println("Wybrany pionek (" + posY1 + "; " + posX1
                            + ") nie należy do pionków które mogą bić: " + mandatory);
                    throw new IncorrectPositionException();
                }
            }
            // Sprawdzamy czy podany ruch jest możliwy i czy aby go wykonać musimy zbić
            // przeciwnika
            int[][] neededEnemyPosition;
            neededEnemyPosition = tempBoard[posY1][posX1].canGoTo(posX1, posY1, posX2, posY2);

            // Jeżeli przeskakujemy o więcej niż 1 pole to sprawdzamy czy pomiędzy nimi jest
            // przeciwnik, jeśli tak to go usuwamy
            int enemiesCount = 0;
            int enemyX = 0;
            int enemyY = 0;
            for (int[] possibleEnemy : neededEnemyPosition) {
                int possibleEnemyX = possibleEnemy[0];
                int possibleEnemyY = possibleEnemy[1];
                if (tempBoard[possibleEnemyY][possibleEnemyX] != null
                        && tempBoard[possibleEnemyY][possibleEnemyX].getColor() == tempBoard[posY1][posX1].getColor()) {
                    System.out.println("Między wybranym pionkiem a miejscem docelowym znajduje się pionek sojusznika");
                    throw new IncorrectPositionException();
                }
                if (tempBoard[possibleEnemyY][possibleEnemyX] != null
                        && tempBoard[possibleEnemyY][possibleEnemyX].getColor() != tempBoard[posY1][posX1].getColor()) {
                    enemiesCount++;
                    enemyX = possibleEnemyX;
                    enemyY = possibleEnemyY;
                }
            }
            if (enemiesCount == 0 && isPieceInMandatory) {
                System.out.println("Wybrany ruch nie wykorzystuje bicia");
                throw new IncorrectPositionException();
            } else if (enemiesCount == 1) {
                if (tempBoard[enemyY][enemyX].getColor() == PieceColorEnum.Red) {
                    board.setNoRedRemaining(board.getNoRedRemaining() - 1);
                } else {
                    board.setNoWhiteRemaining(board.getNoWhiteRemaining() - 1);
                }
                tempBoard[enemyY][enemyX] = null;
            } else if ((enemiesCount == 0 && tempBoard[posY1][posX1].getStateName() == PieceStateEnum.Pawn
                    && neededEnemyPosition.length > 0)
                    || enemiesCount > 1) {
                System.out.println("164");
                throw new IncorrectPositionException();
            }
            // Przesuwamy pionek
            tempBoard[posY2][posX2] = tempBoard[posY1][posX1];
            tempBoard[posY1][posX1] = null;
            board.setMandatoryUsePieces(new int[0][0]);
            board.setPieces(tempBoard);
            board.setTurnOver(true);

            // Jeśli zbiliśmy pionka to sprawdzamy czy ten pionek może wykonać jeszcze jakiś
            // ruch
            if (enemiesCount == 1) {
                if (this.canKill(posX2, posY2)) {
                    int[][] temp = new int[1][2];
                    temp[0][0] = posY2;
                    temp[0][1] = posX2;
                    System.out.println("Poruszaj się nadal pionkiem (" + posY2 + "; " + posX2 + ")");

                    board.setMandatoryUsePieces(temp);
                    board.setTurnOver(false);
                }
            }
            int sideToBecomeQueen = 0;
            if (tempBoard[posY2][posX2].isStartingAtBottom()) {
                sideToBecomeQueen = board.getHeight() - 1;
            }

            if (canBecomeQueen() && posY2 == sideToBecomeQueen) {
                tempBoard[posY2][posX2].upgradePiece();
            }
        } catch (IncorrectPositionException e) {
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("188");
            throw new IncorrectPositionException();
        }

    }

    /**
     * Dodanie pionka który ma obowiązek bicia do listy
     * 
     * @param posX pozycja x pionka
     * @param posY pozycja y pionka
     */
    protected void addMandatory(int posX, int posY) {

        int[][] mandatoryArr = board.getMandatoryUsePieces();
        int[][] tempArr = new int[mandatoryArr.length + 1][2];

        for (int i = 0; i < mandatoryArr.length; i++) {
            tempArr[i] = mandatoryArr[i];
        }

        tempArr[mandatoryArr.length][0] = posY;
        tempArr[mandatoryArr.length][1] = posX;

        board.setMandatoryUsePieces(tempArr);
    }

    /**
     * Rozpoczęcie nowej tury - ustawienie odpowiednich parametrów
     */
    public void startNextTurn() {
        board.setTurnOver(false);
        board.setMandatoryUsePieces(new int[0][0]);
        board.setWhiteTurn(!board.isWhiteTurn());
        calculateMandatory();
    }

    /**
     * Tworzenie stanu planszy do przesłania na podstawie układu pionków
     * 
     * @return tablica dwuwymiarowa z układem planszy
     */
    public SquareStateEnum[][] translateBoard() {
        SquareStateEnum[][] result = new SquareStateEnum[board.getHeight()][board.getWidth()];
        AbstractPiece[][] boardContent = board.getPieces();

        int h = board.getHeight();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if ((i + j) % 2 == 1) {
                    result[h - 1 - i][j] = SquareStateEnum.White;
                } else if (boardContent[i][j] == null) {
                    result[h - 1 - i][j] = SquareStateEnum.BlackEmpty;
                } else if (boardContent[i][j].getColor() == PieceColorEnum.White) {
                    result[h - 1 - i][j] = SquareStateEnum.BlackWhite;
                    if (boardContent[i][j].getStateName() == PieceStateEnum.Queen) {
                        result[h - 1 - i][j] = SquareStateEnum.BlackWhiteQueen;
                    }
                } else if (boardContent[i][j].getColor() == PieceColorEnum.Red) {
                    result[h - 1 - i][j] = SquareStateEnum.BlackRed;
                    if (boardContent[i][j].getStateName() == PieceStateEnum.Queen) {
                        result[h - 1 - i][j] = SquareStateEnum.BlackRedQueen;
                    }
                }
            }
        }

        return result;
    }

    /**
     * Pobieranie rezultatu gry
     * 
     * @return Zwraca wygranego lub null w przypadku niezakończenia gry.
     */
    public String isGameOver() {
        if (board.getNoWhiteRemaining() == 0) {
            return "Red win!";
        } else if (board.getNoRedRemaining() == 0) {
            return "White win!";
        } else {
            return null;
        }
    }
}
