package pl.tp.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import pl.tp.SocketCommandsEnum;
import pl.tp.SquareStateEnum;

public class ServerController {
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    public ServerController() {
        try {
            socket = new Socket("localhost", 4444);
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getLine() throws ServerDownException, Exception {
        try {
            String text = input.readLine();
            if (text == null || SocketCommandsEnum.exit.toString().equals(text)) {
                throw new ServerDownException();
            }
            else {
                return text;
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public void movePiece(String sourceXY, String destinationXY) {
        output.println(sourceXY);
        output.println(destinationXY);
    }
    public void endConnection() {
        output.println(SocketCommandsEnum.exit.toString());
        try {
            input.close();
            output.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private SquareStateEnum[] textToEnum(String line) {
        int size = line.length();
        SquareStateEnum[] result = new SquareStateEnum[size];
        for (int i = 0; i < size; i++) {
            int currEnum = Integer.parseInt(line.charAt(i) + "");
            result[i] = SquareStateEnum.values()[currEnum];
        }
        return result;
    }
    public SquareStateEnum[][] getBoard() throws Exception {
        try {
            String line = this.getLine();
            int size = line.length();
            SquareStateEnum[][] board = new SquareStateEnum[size][size];
            board[0] = this.textToEnum(line);
            for (int i = 1; i < size; i++) {
                line = this.getLine();
                board[i] = this.textToEnum(line);
            }
            return board;
        } catch (Exception e) {
            throw e;
        }
    }
    public void sendDrawResponse(boolean isAccepted) {
        if (isAccepted) {
            output.println(SocketCommandsEnum.acceptDraw.toString());
        } else {
            output.println(SocketCommandsEnum.rejectDraw.toString());            
        }
    }

}
