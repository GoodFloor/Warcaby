package pl.tp;

public class ClassicGameController extends GameController {
    @Override
    void setBoard() {
        boardController = new ClassicBoardController();
        boardController.resetBoard();
    }

    // @Override
    // public void run() {
    // gameView.showMessage("The Classic Game is on");
    // }

}
