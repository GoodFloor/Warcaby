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
                } else if (i > 4) {
                    temp[i][j] = new Piece();
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
        return Integer.parseInt(position.substring(1, position.length())) - 49;
    }
    // public boolean isWhite(String position) {

    // }
    // // public boolean isRed(String position) {

    // }
    // public boolean canKill(String position) {
    // if()
    // }

    public SquareStateEnum[][] translateBoard() {
        SquareStateEnum[][] result = new SquareStateEnum[board.getWidth()][board.getHeight()];
        Piece[][] boardContent = board.getBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardContent[i][j] == null) {
                    result[i][j] = SquareStateEnum.White;
                } else {
                    result[i][j] = SquareStateEnum.BlackEmpty;
                }
            }
        }

        return result;
    }
}
