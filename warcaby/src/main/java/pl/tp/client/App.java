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
                    String[] move = gui.getMove();
                    server.movePiece(move[0], move[1]);
                }
                else if(SocketCommandsEnum.wait.toString().equals(command)) {
                    gui.endMove();
                }
                else if(SocketCommandsEnum.proposeDraw.toString().equals(command)) {
                    System.out.println("Draw proposed!");
                    gui.drawProposed();
                    server.sendDrawResponse(gui.getDrawResponse());
                }
                else if(SocketCommandsEnum.acceptDraw.toString().equals(command)) {
                    gui.endGame("Remis");
                }
                else if(SocketCommandsEnum.rejectDraw.toString().equals(command)) {
                    gui.printMessage("Odrzucono propozycję remisu");
                    gui.endDrawDiscussion();
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(SocketCommandsEnum.exit.toString().equals(command)) {
                    gui.close();
                }
                else {
                    System.out.println(command);
                    gui.printMessage(command);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        server.endConnection();
        System.out.println("Zakończono połączenie");
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gui.close();
    }
}
