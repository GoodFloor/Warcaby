package pl.tp.server;

/**
 * Klasa obsługująca konkretną rozgrywkę klasycznego typu
 * 
 * Produkt konkretny we wzorcu Factory
 * Klasa konkretna we wzorcu Template method
 */
public class PolishGameController extends GameController {
    @Override
    void setBoard() {
        boardController = new PolishBoardController();
        boardController.resetBoard();
    }

    @Override
    void setStartMessage() {
        this.startMessage = "The Polish game is on";
    }
}
