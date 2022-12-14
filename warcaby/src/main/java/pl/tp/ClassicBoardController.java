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

    private int getPositionX(String position) {
        return (int) position.charAt(0) - 65;
    }

    private int getPositionY(String position) {
        return Integer.parseInt(position.substring(1, position.length())) - 1;
    }

    public boolean isWhite(String position) {
        return board.getBoard()[this.getPositionX(position)][this.getPositionY(position)].getType() == "White";
    }

    public boolean isRed(String position) {
        return board.getBoard()[this.getPositionX(position)][this.getPositionY(position)].getType() == "Red";
    }

    public boolean canKill(String position) {
        Piece[][] tempBoard = board.getBoard();

        int posX = this.getPositionX(position);
        int posY = this.getPositionY(position);
        if (tempBoard[posX][posY].getClass() == Piece.class) {
            return true;
        }
        return false;
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
