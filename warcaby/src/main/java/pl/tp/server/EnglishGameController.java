package pl.tp.server;

/**
 * Klasa obsługująca konkretną rozgrywkę klasycznego typu
 * 
 * Produkt konkretny we wzorcu Factory
 * Klasa konkretna we wzorcu Template method
 */
public class EnglishGameController extends GameController {
    @Override
    void setBoard() {
        boardController = new EnglishBoardController();
        boardController.resetBoard();
    }

    @Override
    void setStartMessage() {
        this.startMessage = "The English game is on";
    }
}
