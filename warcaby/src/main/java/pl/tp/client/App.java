package pl.tp.client;

public class App {

    public static void main(String[] args) {
        try {
            GameController game = new GameController();
            game.run();  
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
