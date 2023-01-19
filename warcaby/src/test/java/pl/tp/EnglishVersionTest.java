package pl.tp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pl.tp.server.EnglishBoardController;
import pl.tp.server.IncorrectPositionException;

public class EnglishVersionTest {

    @Test
    public void checkEnglishBoardStartTest() {
        EnglishBoardController boardC = new EnglishBoardController();

        SquareStateEnum[][] board = boardC.translateBoard();

        assertEquals(board.length, 8);
        assertEquals(board[0][0], SquareStateEnum.White);
        assertEquals(board[0][1], SquareStateEnum.BlackRed);
        assertEquals(board[1][0], SquareStateEnum.BlackRed);
        assertEquals(board[1][1], SquareStateEnum.White);
        assertEquals(board[7][0], SquareStateEnum.BlackWhite);
    }

    @Test
    public void moveWhiteOkTest() {
        EnglishBoardController boardC = new EnglishBoardController();

        try {
            boardC.movePiece("A3", "B4");
        } catch (Exception e) {
        }

        SquareStateEnum[][] board = boardC.translateBoard();

        assertEquals(board.length, 8);
        assertEquals(board[5][0], SquareStateEnum.BlackEmpty);
        assertEquals(board[4][1], SquareStateEnum.BlackWhite);
    }

    @Test
    public void moveWhiteToWhiteTest() {
        EnglishBoardController boardC = new EnglishBoardController();

        try {
            boardC.movePiece("A3", "B3");
        } catch (IncorrectPositionException e) {
            assertTrue(true);
        }
    }

    @Test
    public void moveWhiteToOccupiedTest() {
        EnglishBoardController boardC = new EnglishBoardController();

        try {
            boardC.movePiece("A0", "B1");
        } catch (IncorrectPositionException e) {
            assertTrue(true);
        }
    }

    @Test
    public void moveRedWhenWhiteTurnTest() {
        EnglishBoardController boardC = new EnglishBoardController();

        try {
            boardC.movePiece("B6", "C5");
        } catch (IncorrectPositionException e) {
            assertTrue(true);
        }
    }

    @Test
    public void moveObligatoryOkTest() {
        EnglishBoardController boardC = new EnglishBoardController();

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
        EnglishBoardController boardC = new EnglishBoardController();

        try {
            boardC.movePiece("C3", "D4");
            boardC.movePiece("B6", "C5");
            boardC.movePiece("D4", "E5");
        } catch (IncorrectPositionException e) {
            assertTrue(true);
        }
    }

    @Test
    public void moveDespiteObligatoryTest() {
        EnglishBoardController boardC = new EnglishBoardController();

        try {
            boardC.movePiece("C3", "D4");
            boardC.movePiece("B6", "C5");
            boardC.movePiece("A3", "B4");
        } catch (IncorrectPositionException e) {
            assertTrue(true);
        }
    }

    @Test
    public void makeQueenTest() {
        EnglishBoardController boardC = new EnglishBoardController();

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
        EnglishBoardController boardC = new EnglishBoardController();

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
        EnglishBoardController boardC = new EnglishBoardController();

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
            assertTrue(true);
        }
    }

}
