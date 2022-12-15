package pl.tp;

public class ClassicBoardController extends BoardController {
    int whiteRemaining;
    int redRemaining;
    int[][] mandatoryUsePieces;
    boolean whiteTurn;
    public void resetBoard() {
        // board[0][0] to lewy dolny róg, pierwsza współrzędna to wiersz, druga to
        // kolumna ale to i tak tylko tymczasowe
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
        board.setBoard(temp);
        isTurnOver = false;
        mandatoryUsePieces = new int[0][0];
        whiteRemaining = 12;
        redRemaining = 12;
        whiteTurn = true;
    }

    private int decodePositionX(String position) throws IncorrectPositionException {
        try {
            return (int) position.charAt(0) - 65;
        } catch (Exception e) {
            throw new IncorrectPositionException();
        }
    }

    private int decodePositionY(String position) throws IncorrectPositionException {
        try {
            return Integer.parseInt(position.substring(1, position.length())) - 1;
        } catch (Exception e) {
            throw new IncorrectPositionException();
        }
    }

    private String codePosition(int posX, int posY) {
        String result = "";
        result += (char) (posX + 65);
        result += posY + 1;
        return result;
    }

    // public boolean isWhite(String position) {
    // return
    // board.getBoard()[this.decodePositionX(position)][this.decodePositionY(position)].getColor()
    // == "White";
    // }

    // public boolean isRed(String position) {
    // return
    // board.getBoard()[this.decodePositionX(position)][this.decodePositionY(position)].getColor()
    // == "Red";
    // }

    private boolean canKill(int posX, int posY) {
        Piece[][] tempBoard = board.getBoard();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int tempX = i * 4 - 2 + posX;
                int tempY = j * 4 - 2 + posY;
                if(tempX < 0 || tempX >= tempBoard.length || tempY < 0 || tempY >= tempBoard.length) {
                    continue;
                }
                if(tempBoard[tempY][tempX] != null) {
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
                if (tempBoard[enemyY][enemyX] != null && tempBoard[enemyY][enemyX].getColor() != tempBoard[posY][posX].getColor() ) {
                    return true;
                }
            }
        }
        return false;

        // if (posX > 1 && posY < 6 && tempBoard[posX - 1][posY - 1].getClass() == Piece.class
        //         && tempBoard[posX][posY].getColor() != tempBoard[posX - 1][posY - 1].getColor()
        //         && tempBoard[posX - 2][posY - 2] == null) {
        //     return true;
        // } else if (posX < 6 && posY < 6 && tempBoard[posX + 1][posY + 1].getClass() == Piece.class
        //         && tempBoard[posX][posY].getColor() != tempBoard[posX + 1][posY + 1].getColor()
        //         && tempBoard[posX + 2][posY + 2] == null) {
        //     return true;
        // } else
        //     return false;
    }

    // public String whoCanKill(String type) {
    //     Piece[][] tempBoard = board.getBoard();
    //     String result = "";
    //     for (int i = 0; i < tempBoard.length; i++) {
    //         for (int j = 0; j < tempBoard[i].length; j++) {
    //             if (tempBoard[i][j].getColor() == type && canKill(i, j)) {
    //                 result += this.codePosition(i, j) + ";";
    //             }
    //         }
    //     }
    //     return result;
    // }

    public void movePiece(String pos1, String pos2) throws IncorrectPositionException {

        try {
            //Konwertujemy miejsce źródłowe pionka i miejsce docelowe pionka
            int posX1 = this.decodePositionX(pos1);
            int posY1 = this.decodePositionY(pos1);
            int posX2 = this.decodePositionX(pos2);
            int posY2 = this.decodePositionY(pos2);

            //Pobieramy zawartość planszy
            Piece[][] tempBoard = board.getBoard();

            
            //Sprawdzamy czy na miejscu źródłowym jest pionek i czy miejsce docelowe jest puste 
            if(tempBoard[posY1][posX1] == null || tempBoard[posY2][posX2] != null) {
                throw new IncorrectPositionException();
            }
            //Sprawdzamy czy pionek źródłowy jest w dobrym kolorze
            if((whiteTurn && tempBoard[posY1][posX1].getColor() != "White") || (!whiteTurn && tempBoard[posY1][posX1].getColor() != "Red")) {
                throw new IncorrectPositionException();
            }
            //Sprawdzamy czy podany pionek jest pośród pionków którymi trzeba się ruszyć
            if(mandatoryUsePieces.length > 0) {
                boolean isPieceInMandatory = false;
                for (int[] mandatoryPiece : mandatoryUsePieces) {
                    if(mandatoryPiece[0] == posX1 && mandatoryPiece[1] == posY1 && Math.abs(posX1 - posX2) == 2 && Math.abs(posY1 - posY2) == 2) {
                        isPieceInMandatory = true;
                        break;
                    }
                }
                if(!isPieceInMandatory) {
                    throw new IncorrectPositionException();
                }
            }
            //Sprawdzamy czy podany ruch jest możliwy i czy aby go wykonać musimy zbić przeciwnika
            int[] neededEnemyPosition;
            neededEnemyPosition = tempBoard[posY1][posX1].canGoTo(posX1, posY1, posX2, posY2);
            
            //Jeżeli przeskakujemy o 2 pola to sprawdzamy czy pomiędzy nimi jest przeciwnik, jeśli tak to go usuwamy
            if(neededEnemyPosition.length > 0) {
                int enemyX = neededEnemyPosition[0];
                int enemyY = neededEnemyPosition[1];
                if (tempBoard[enemyY][enemyX] == null || tempBoard[enemyY][enemyX].getColor() == tempBoard[posY1][posX1].getColor()) {
                    throw new IncorrectPositionException();                    
                } else {
                    if(tempBoard[enemyY][enemyX].getColor() == "Red")
                        redRemaining--;
                    else
                        whiteRemaining--;
                    tempBoard[enemyY][enemyX] = null;
                }
            }
            //Przesuwamy pionek
            tempBoard[posY2][posX2] = tempBoard[posY1][posX1];
            tempBoard[posY1][posX1] = null;
            mandatoryUsePieces = new int[0][0];
            board.setBoard(tempBoard);
            isTurnOver = true;

            //Jeśli zbiliśmy pionka to sprawdzamy czy ten pionek może wykonać jeszcze jakiś ruch
            if(neededEnemyPosition.length > 0) {
                if(this.canKill(posX2, posY2)) {
                    mandatoryUsePieces = new int[1][2];
                    mandatoryUsePieces[0][0] = posX2;
                    mandatoryUsePieces[0][1] = posY2;
                }
            }
        } catch (IncorrectPositionException e) {
            throw e;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncorrectPositionException();
        }

    }

    public SquareStateEnum[][] translateBoard() {
        SquareStateEnum[][] result = new SquareStateEnum[8][8];
        Piece[][] boardContent = board.getBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) {
                    result[7 - i][j] = SquareStateEnum.White;
                } else if (boardContent[i][j] == null) {
                    result[7 - i][j] = SquareStateEnum.BlackEmpty;
                } else if (boardContent[i][j].getColor() == "White") {
                    result[7 - i][j] = SquareStateEnum.BlackWhite;
                } else if (boardContent[i][j].getColor() == "Red") {
                    result[7 - i][j] = SquareStateEnum.BlackRed;
                }
            }
        }

        return result;
    }

    private void addMandatory(int posX, int posY) {
        int[][] tempArr = new int[mandatoryUsePieces.length + 1][2];
        for (int i = 0; i < mandatoryUsePieces.length; i++) {
            tempArr[i] = mandatoryUsePieces[i];
        }
        tempArr[mandatoryUsePieces.length][0] = posX;
        tempArr[mandatoryUsePieces.length][1] = posY;
        mandatoryUsePieces = tempArr;
    }
    @Override
    public void startNextTurn() {
        isTurnOver = false;
        mandatoryUsePieces = new int[0][0];
        whiteTurn = !whiteTurn;
        Piece[][] tempBoard = board.getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(tempBoard[i][j] != null && ((whiteTurn && tempBoard[i][j].getColor() == "White") || (!whiteTurn && tempBoard[i][j].getColor() == "Red")) && this.canKill(j, i)) {
                    this.addMandatory(j, i);
                }
            }
        }
    }

    @Override
    public String isGameOver() {
        if(whiteRemaining == 0) {
            return "White win!";
        }
        else if(redRemaining == 0) {
            return "Red win!";
        }
        else {
            return null;
        }
    }
}
