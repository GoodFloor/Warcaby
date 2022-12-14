package pl.tp;

public class ClassicGameController extends GameController {
    @Override
    void setBoard() {
        boardController = new ClassicBoardController();
        boardController.resetBoard();
    }

    @Override
    public void run() {
        System.out.println("The Basic Game is on.");
    }

}
