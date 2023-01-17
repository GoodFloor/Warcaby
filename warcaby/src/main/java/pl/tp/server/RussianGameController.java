package pl.tp.server;

/**
 * Klasa obsługująca konkretną rozgrywkę klasycznego typu
 * 
 * Produkt konkretny we wzorcu Factory
 * Klasa konkretna we wzorcu Template method
 */
public class RussianGameController extends GameController {
    @Override
    void setBoard() {
        boardController = new RussianBoardController();
        boardController.resetBoard();
    }

    @Override
    void setStartMessage() {
        this.startMessage = "The Russian game is on";
    }
}
