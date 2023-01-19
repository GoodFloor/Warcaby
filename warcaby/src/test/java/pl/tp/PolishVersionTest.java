package pl.tp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pl.tp.server.IncorrectPositionException;
import pl.tp.server.PolishBoardController;

public class PolishVersionTest {

    @Test
    public void checkPolishBoardStartTest() {
        PolishBoardController boardC = new PolishBoardController();

        SquareStateEnum[][] board = boardC.translateBoard();

        assertEquals(board.length, 10);
        assertEquals(board[0][0], SquareStateEnum.White);
        assertEquals(board[0][1], SquareStateEnum.BlackRed);
        assertEquals(board[1][0], SquareStateEnum.BlackRed);
        assertEquals(board[1][1], SquareStateEnum.White);
        assertEquals(board[9][0], SquareStateEnum.BlackWhite);
    }

    @Test
    public void moveWhiteOkTest() {
        PolishBoardController boardC = new PolishBoardController();

        try {
            boardC.movePiece("B4", "A5");
        } catch (Exception e) {
        }

        SquareStateEnum[][] board = boardC.translateBoard();

        assertEquals(board[6][1], SquareStateEnum.BlackEmpty);
        assertEquals(board[5][0], SquareStateEnum.BlackWhite);
    }

    @Test
    public void moveWhiteToWhiteTest() {
        PolishBoardController boardC = new PolishBoardController();

        try {
            boardC.movePiece("A3", "B3");
        } catch (IncorrectPositionException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void moveWhiteToOccupiedTest() {
        PolishBoardController boardC = new PolishBoardController();

        try {
            boardC.movePiece("A0", "B1");
        } catch (IncorrectPositionException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void moveRedWhenWhiteTurnTest() {
        PolishBoardController boardC = new PolishBoardController();

        try {
            boardC.movePiece("C5", "B4");
        } catch (IncorrectPositionException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void moveObligatoryOkTest() {
        PolishBoardController boardC = new PolishBoardController();

        try {
            boardC.movePiece("C3", "D4");
            boardC.movePiece("B6", "C5");
            boardC.movePiece("D4", "B6");

            SquareStateEnum[][] board = boardC.translateBoard();

            assertEquals(board[2][3], SquareStateEnum.BlackEmpty);
            assertEquals(board[3][4], SquareStateEnum.BlackEmpty);
            assertEquals(board[1][2], SquareStateEnum.BlackWhite);

        } catch (IncorrectPositionException e) {
        }

    }

    @Test
    public void moveWrongWayObligatoryTest() {
        PolishBoardController boardC = new PolishBoardController();

        try {
            boardC.movePiece("C3", "D4");
            boardC.movePiece("B6", "C5");
            boardC.movePiece("D4", "E5");
        } catch (IncorrectPositionException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void moveDespiteObligatoryTest() {
        PolishBoardController boardC = new PolishBoardController();

        try {
            boardC.movePiece("C3", "D4");
            boardC.movePiece("B6", "C5");
            boardC.movePiece("A3", "B4");
        } catch (IncorrectPositionException e) {
            assertTrue(e != null);
        }
    }

    @Test
    public void makeQueenTest() {
        PolishBoardController boardC = new PolishBoardController();

        try {
            boardC.movePiece("A3", "B4");
            boardC.movePiece("D6", "E5");
            boardC.movePiece("B4", "A5");
            boardC.movePiece("C7", "D6");
            boardC.movePiece("A5", "C7");
            boardC.movePiece("D8", "B6");
            boardC.movePiece("C3", "B4");
            boardC.movePiece("H6", "G5");
            boardC.movePiece("B4", "A5");
            boardC.movePiece("G5", "H4");
            boardC.movePiece("A5", "C7");
            boardC.movePiece("F6", "G5");
            boardC.movePiece("C7", "D8");

            SquareStateEnum[][] board = boardC.translateBoard();

            assertEquals(board[0][3], SquareStateEnum.BlackWhiteQueen);

        } catch (IncorrectPositionException e) {
        }
    }

    @Test
    public void moveQueenOkTest() {
        PolishBoardController boardC = new PolishBoardController();

        try {
            boardC.movePiece("A3", "B4");
            boardC.movePiece("D6", "E5");
            boardC.movePiece("B4", "A5");
            boardC.movePiece("C7", "D6");
            boardC.movePiece("A5", "C7");
            boardC.movePiece("D8", "B6");
            boardC.movePiece("C3", "B4");
            boardC.movePiece("H6", "G5");
            boardC.movePiece("B4", "A5");
            boardC.movePiece("G5", "H4");
            boardC.movePiece("A5", "C7");
            boardC.movePiece("F6", "G5");
            boardC.movePiece("C7", "D8");
            boardC.movePiece("A7", "B6");
            boardC.movePiece("D8", "F6");
            boardC.movePiece("F6", "D4");

            SquareStateEnum[][] board = boardC.translateBoard();

            assertEquals(board[3][4], SquareStateEnum.BlackWhiteQueen);
            assertEquals(board[2][5], SquareStateEnum.BlackEmpty);
            assertEquals(board[4][1], SquareStateEnum.BlackEmpty);

        } catch (IncorrectPositionException e) {
        }
    }

    @Test
    public void moveQueenNotOkTest() {
        PolishBoardController boardC = new PolishBoardController();

        try {
            boardC.movePiece("A3", "B4");
            boardC.movePiece("D6", "E5");
            boardC.movePiece("B4", "A5");
            boardC.movePiece("C7", "D6");
            boardC.movePiece("A5", "C7");
            boardC.movePiece("D8", "B6");
            boardC.movePiece("C3", "B4");
            boardC.movePiece("H6", "G5");
            boardC.movePiece("B4", "A5");
            boardC.movePiece("G5", "H4");
            boardC.movePiece("A5", "C7");
            boardC.movePiece("F6", "G5");
            boardC.movePiece("C7", "D8");
            boardC.movePiece("A7", "B6");
            boardC.movePiece("D8", "F6");
            boardC.movePiece("F6", "E7");

        } catch (IncorrectPositionException e) {
            assertTrue(e != null);
        }
    }
}
