package pl.tp.client;

import pl.tp.SocketCommandsEnum;
import pl.tp.SquareStateEnum;

public class App {

    public static void main(String[] args) {
        ServerController server = new ServerController();
        View gui = new WindowView();
        boolean isPlayer1;
        int currentBoardSize = 8;
        try {
            if(server.getLine().equals(SocketCommandsEnum.player1.toString())) {
                isPlayer1 = true;
            }
            else {
                isPlayer1 = false;
            }
            while(true) {
                String command = server.getLine();
                if(SocketCommandsEnum.drawBoard.toString().equals(command)) {
                    int size = Integer.parseInt(server.getLine());
                    gui.newBoard(size);
                    currentBoardSize = size;
                }
                else if(SocketCommandsEnum.printPieces.toString().equals(command)) {
                    gui.drawBoard(server.getBoard());
                }
                else if(SocketCommandsEnum.getMove.toString().equals(command)) {
                    gui.printMessage("Wykonaj ruch!");
                    String[] move = gui.getMove();
                    server.movePiece(move[0], move[1]);
                    System.out.println(move[0] + ", " + move[1]);
                }
                else if(SocketCommandsEnum.wait.toString().equals(command)) {
                    gui.printMessage("Oczekiwanie na drugiego gracza");
                }
                else {
                    System.out.println(command);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        server.endConnection();
        System.out.println("Zakończono połączenie");
        gui.close();
    }
}
