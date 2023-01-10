package pl.tp.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import pl.tp.SquareStateEnum;

public class SocketView implements GameView{
    private ServerSocket serverSocket;
    private Socket socketPlayer1;
    private Socket socketPlayer2;
    private BufferedReader inputPlayer1;
    private BufferedReader inputPlayer2;
    private PrintWriter outputPlayer1;
    private PrintWriter outputPlayer2;
    private boolean player1Turn;

    SocketView() {
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server is listening on port 4444");            
        } catch (Exception e) {
            System.out.println("Błąd uruchomienia serwera");
        }
    }
    @Override
    public void printMessage(String message) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void printException(Exception e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void printBoard(SquareStateEnum[][] boardContent) {
        int size = boardContent.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%d", boardContent[i][j].ordinal());
            }
            System.out.printf("\n");
        }        
    }

    @Override
    public String[] getMove() {
        String[] temp = new String[2];
        try {
            wait();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        temp[0] = "A1";
        temp[1] = "B2";
        // TODO Auto-generated method stub
        return temp;
    }

    @Override
    public void start() {
        try {
            //Oczekiwanie na gracza 1
            socketPlayer1 = serverSocket.accept();
            System.out.println("Gracz 1 dołączył!");
            InputStream in1 = socketPlayer1.getInputStream();
            inputPlayer1 = new BufferedReader(new InputStreamReader(in1));
            OutputStream out1 = socketPlayer1.getOutputStream();
            outputPlayer1 = new PrintWriter(out1, true);

            //Oczekiwanie na gracza 2
            socketPlayer2 = serverSocket.accept();
            System.out.println("Gracz 2 dołączył");
            InputStream in2 = socketPlayer2.getInputStream();
            inputPlayer2 = new BufferedReader(new InputStreamReader(in2));
            OutputStream out2 = socketPlayer2.getOutputStream();
            outputPlayer2 = new PrintWriter(out2, true);
        } catch (Exception e) {
            System.out.println("Błąd podłączenia klientów");
        }
        player1Turn = true;
    }

    @Override
    public void end() {
        try {
            socketPlayer1.close();
            socketPlayer2.close();
        } catch (Exception e) {
            System.out.println("Nieudana próba zamknięcia połączenia z klientem");
        }
        
    }
    
}
