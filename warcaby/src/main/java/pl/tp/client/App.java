package pl.tp.client;

import pl.tp.SocketCommandsEnum;

/**
 * Główna klasa programu - kontroler we wzorcu MVC
 */
public class App {
    /**
     * Główna metoda programu
     * 
     * @param args argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        ServerView server = new ServerView();
        View gui = new WindowView();
        try {
            while (true) {
                if (gui.isExited()) {
                    server.endConnection();
                    break;
                }
                String command = server.getLine();
                if (SocketCommandsEnum.drawBoard.toString().equals(command)) {
                    int size = Integer.parseInt(server.getLine());
                    gui.newBoard(size);
                } else if (SocketCommandsEnum.printPieces.toString().equals(command)) {
                    gui.drawBoard(server.getBoard());
                } else if (SocketCommandsEnum.getMove.toString().equals(command)) {
                    String[] move = gui.getMove();
                    server.movePiece(move[0], move[1]);
                } else if (SocketCommandsEnum.wait.toString().equals(command)) {
                    gui.endMove();
                } else if (SocketCommandsEnum.proposeDraw.toString().equals(command)) {
                    System.out.println("Draw proposed!");
                    gui.drawProposed();
                    server.sendDrawResponse(gui.getDrawResponse());
                } else if (SocketCommandsEnum.acceptDraw.toString().equals(command)) {
                    gui.endGame("Remis");
                } else if (SocketCommandsEnum.rejectDraw.toString().equals(command)) {
                    gui.printMessage("Odrzucono propozycję remisu");
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (SocketCommandsEnum.exit.toString().equals(command)) {
                    break;
                } else {
                    if (command.charAt(2) != ';') {
                        System.out.println(command);
                        gui.printMessage(command);
                    }
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
        gui.printMessage("Utracono połączenie z serwerem");
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        gui.close();
    }
}
