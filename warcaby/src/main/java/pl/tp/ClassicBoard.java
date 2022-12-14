package pl.tp;

public class ClassicBoard extends Board{
    private int width;
    private int height;
    private Piece[][] board;
    ClassicBoard() {
        this.width = 8;
        this.height = 8;
        board = new Piece[width][height];
    }
    public void resetBoard() {
        //board[0][0] to lewy dolny róg, pierwsza współrzędna to wiersz, druga to kolumna ale to i tak tylko tymczasowe
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((i + j) % 2 == 1) {
                    board[i][j] = null;
                }
                else if(i < 3) {
                    board[i][j] = new Piece();
                }
                else if(i > 4) {
                    board[i][j] = new Piece();
                }
                else {
                    board[i][j] = null;
                }
            }
        }
    }
}
