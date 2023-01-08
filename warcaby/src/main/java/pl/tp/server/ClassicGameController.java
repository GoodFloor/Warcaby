package pl.tp.server;

/**
 * Klasa obsługująca konkretną rozgrywkę klasycznego typu
 * 
 * Produkt konkretny we wzorcu Factory
 * Klasa konkretna we wzorcu Template method
 */
public class ClassicGameController extends GameController {
    @Override
    void setBoard() {
        boardController = new ClassicBoardController();
        boardController.resetBoard();
    }

    @Override
    void setStartMessage() {
        this.startMessage = "The Classic game is on";
    }
}
