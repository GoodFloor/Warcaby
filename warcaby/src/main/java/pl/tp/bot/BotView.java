package pl.tp.bot;

public class BotView {
    boolean isExited;
    String move;

    BotView() {
        isExited = false;
    }

    public void close() {
        isExited = true;
        System.exit(0);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void saveMove(String move) {
        this.move = move;
        System.out.println(move);
    }

    public String[] getMove() {
        String[] result = new String[2];
        result[0] = move.substring(0, 2);
        result[1] = move.substring(3, 5);

        System.out.println(result[0] + " - > " + result[1]);
        return result;
    }

    public boolean getDrawResponse() {
        return true;
    }

    public boolean isExited() {
        return isExited;
    }

}
