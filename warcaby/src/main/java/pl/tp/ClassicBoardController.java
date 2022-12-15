package pl.tp;

public class ClassicBoardController extends BoardController {
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
                    temp[i][j].setType("White");
                } else if (i > 4) {
                    temp[i][j] = new Piece();
                    temp[i][j].setType("Red");
                } else {
                    temp[i][j] = null;
                }
            }
        }
        board.setBoard(temp);
    }

    private int decodePositionX(String position) {
        return (int) position.charAt(0) - 65;
    }

    private int decodePositionY(String position) {
        return Integer.parseInt(position.substring(1, position.length())) - 1;
    }

    private String codePosition(int posX, int posY) {
        String result = "";
        result += (char) (posX + 65);
        result += posY + 1;
        return result;
    }

    public boolean isWhite(String position) {
        return board.getBoard()[this.decodePositionX(position)][this.decodePositionY(position)].getType() == "White";
    }

    public boolean isRed(String position) {
        return board.getBoard()[this.decodePositionX(position)][this.decodePositionY(position)].getType() == "Red";
    }

    private boolean canKill(int posX, int posY) {
        Piece[][] tempBoard = board.getBoard();
        if (posX > 1 && posY < 6 && tempBoard[posX - 1][posY - 1].getClass() == Piece.class
                && tempBoard[posX][posY].getType() != tempBoard[posX - 1][posY - 1].getType()
                && tempBoard[posX - 2][posY - 2] == null) {
            return true;
        } else if (posX < 6 && posY < 6 && tempBoard[posX + 1][posY + 1].getClass() == Piece.class
                && tempBoard[posX][posY].getType() != tempBoard[posX + 1][posY + 1].getType()
                && tempBoard[posX + 2][posY + 2] == null) {
            return true;
        } else
            return false;
    }

    public String whoCanKill(String type) {
        Piece[][] tempBoard = board.getBoard();
        String result = "";
        for (int i = 0; i < tempBoard.length; i++) {
            for (int j = 0; j < tempBoard[i].length; j++) {
                if (tempBoard[i][j].getType() == type && canKill(i, j)) {
                    result += this.codePosition(i, j) + ";";
                }
            }
        }
        return result;
    }

    public void movePiece(String pos1, String pos2) {
        int posX1 = this.decodePositionX(pos1);
        int posY1 = this.decodePositionY(pos1);
        int posX2 = this.decodePositionX(pos2);
        int posY2 = this.decodePositionY(pos2);
        
        Piece[][] tempBoard = board.getBoard();
        tempBoard[posY2][posX2] = tempBoard[posY1][posX1];
        tempBoard[posY1][posX1] = null;
        board.setBoard(tempBoard);
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
                } else if (boardContent[i][j].getType() == "White") {
                    result[7 - i][j] = SquareStateEnum.BlackWhite;
                } else if (boardContent[i][j].getType() == "Red") {
                    result[7 - i][j] = SquareStateEnum.BlackRed;
                }
            }
        }

        return result;
    }
}
