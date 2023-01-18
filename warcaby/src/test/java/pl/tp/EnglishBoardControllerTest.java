package pl.tp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pl.tp.server.EnglishBoardController;
import pl.tp.server.IncorrectPositionException;

public class EnglishBoardControllerTest {

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

}
