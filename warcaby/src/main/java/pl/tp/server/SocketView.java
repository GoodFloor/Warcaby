package pl.tp.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import pl.tp.SocketCommandsEnum;
import pl.tp.SquareStateEnum;

public class SocketView implements GameView{
    private ServerSocket serverSocket;
    private Socket socketPlayer1;
    private Socket socketPlayer2;
    private BufferedReader inputPlayer1;
    private BufferedReader inputPlayer2;
    private PrintWriter outputPlayer1;
    private PrintWriter outputPlayer2;
    private TerminalView terminalView;
    private boolean boardNotDrawn;

    SocketView() {
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server is listening on port 4444");
            boardNotDrawn = true;
            terminalView = new TerminalView();
            System.out.println(SocketCommandsEnum.player1.toString());
        } catch (Exception e) {
            System.out.println("Błąd uruchomienia serwera");
        }
    }
    @Override
    public void printMessage(String message) {
        terminalView.printMessage(message);

        // TODO Auto-generated method stub
        
    }

    @Override
    public void printException(Exception e) {
        terminalView.printException(e);
        // TODO Auto-generated method stub
        
    }

    @Override
    public void printBoard(SquareStateEnum[][] boardContent) {
        terminalView.printBoard(boardContent);
        int size = boardContent.length;
        if (boardNotDrawn) {
            outputPlayer1.println(SocketCommandsEnum.drawBoard.toString());
            outputPlayer1.println(size);
            outputPlayer2.println(SocketCommandsEnum.drawBoard.toString());
            outputPlayer2.println(size);
            boardNotDrawn = false;
        }
        outputPlayer1.println(SocketCommandsEnum.printPieces.toString());
        outputPlayer2.println(SocketCommandsEnum.printPieces.toString());
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                outputPlayer1.print(boardContent[i][j].ordinal());
                outputPlayer2.print(boardContent[i][j].ordinal());
            }
            outputPlayer1.printf("\n");
            outputPlayer2.printf("\n");
        }        
    }

    @Override
    public String[] getMove(boolean fromPlayer1) {
        String[] temp = new String[2];
        try {
            if(fromPlayer1) {
                outputPlayer2.println(SocketCommandsEnum.wait.toString());
                outputPlayer1.println(SocketCommandsEnum.getMove.toString());
                temp[0] = inputPlayer1.readLine();
                System.out.println(temp[0]);
                temp[1] = inputPlayer1.readLine();
            }
            else {
                outputPlayer1.println(SocketCommandsEnum.wait.toString());
                outputPlayer2.println(SocketCommandsEnum.getMove.toString());
                temp[0] = inputPlayer2.readLine();
                temp[1] = inputPlayer2.readLine();
            }
        } catch (Exception e) {
            System.out.println("Błąd odczytu danych od klienta");
            return new String[2];
        }
        return temp;
    }

    @Override
    public void start() {
        terminalView.start();
        try {
            //Oczekiwanie na gracza 1
            socketPlayer1 = serverSocket.accept();
            System.out.println("Gracz 1 dołączył!");
            InputStream in1 = socketPlayer1.getInputStream();
            inputPlayer1 = new BufferedReader(new InputStreamReader(in1));
            OutputStream out1 = socketPlayer1.getOutputStream();
            outputPlayer1 = new PrintWriter(out1, true);
            outputPlayer1.println(SocketCommandsEnum.player1.toString());


            //Oczekiwanie na gracza 2
            socketPlayer2 = serverSocket.accept();
            System.out.println("Gracz 2 dołączył");
            InputStream in2 = socketPlayer2.getInputStream();
            inputPlayer2 = new BufferedReader(new InputStreamReader(in2));
            OutputStream out2 = socketPlayer2.getOutputStream();
            outputPlayer2 = new PrintWriter(out2, true);
            outputPlayer2.println(SocketCommandsEnum.player2.toString());
        } catch (Exception e) {
            System.out.println("Błąd podłączenia klientów");
        }
    }

    @Override
    public void end() {
        terminalView.end();
        try {
            socketPlayer1.close();
            socketPlayer2.close();
        } catch (Exception e) {
            System.out.println("Nieudana próba zamknięcia połączenia z klientem");
        }
        
    }
    
}
