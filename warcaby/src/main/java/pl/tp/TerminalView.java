package pl.tp;

public class TerminalView implements GameView {

    @Override
    public void showMessage(String message) {
        System.out.println("The Game is on.");
    }

}
