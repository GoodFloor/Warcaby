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
                if(SocketCommandsEnum.drawBoard.toString().equals(line)) {
                    boardSize = Integer.parseInt(server.getLine());
                    gui = new TerminalView(boardSize);
                } 
                else if(line == SocketCommandsEnum.printPieces.toString()) {
                    gui.updatePieces(server.getBoard(boardSize));
                } 
                else if(line == SocketCommandsEnum.getMove.toString()) {
                    String[] move = gui.getMove();
                    server.sendMoveRequest(move[0], move[1]);
                } 
                else if(line == SocketCommandsEnum.alert.toString()) {
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
