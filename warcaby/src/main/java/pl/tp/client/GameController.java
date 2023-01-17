package pl.tp.client;

import pl.tp.SocketCommandsEnum;

public class GameController {
    SocketView server;
    View gui;
    int boardSize;
    boolean amIPlayer1;
    boolean isGameRunning;
    GameController() throws Exception{
        try {
            server = new SocketView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            if(server.getLine() == SocketCommandsEnum.player1.toString()) {
                amIPlayer1 = true;
            }
            else {
                amIPlayer1 = false;
            }
            isGameRunning = true;
            while(isGameRunning) {
                String line = server.getLine();
                System.out.println(line);
                if(SocketCommandsEnum.drawBoard.toString().equals(line)) {
                    boardSize = Integer.parseInt(server.getLine());
                    gui = new TerminalView(boardSize);
                } 
                else if(SocketCommandsEnum.printPieces.toString().equals(line)) {
                    gui.updatePieces(server.getBoard(boardSize));
                } 
                else if(SocketCommandsEnum.getMove.toString().equals(line)) {
                    String[] move = gui.getMove();
                    server.sendMoveRequest(move[0], move[1]);
                } 
                else if(SocketCommandsEnum.alert.toString().equals(line)) {
                    gui.printMessage(server.getLine());
                }            
            }
        } catch (ServerErrorException serverDown) {
            this.end();
            return;
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void end() {
        try {
            isGameRunning = false;
            server.endConnection();
        } catch (Exception e) {
            System.out.println("Nieudana próba zamknięcia połączenia " + e);
        }
    }
}
