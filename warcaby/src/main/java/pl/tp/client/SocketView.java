package pl.tp.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import pl.tp.SquareStateEnum;

public class SocketView {
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    SocketView() throws Exception {
        try {
            socket = new Socket("localhost", 4444);
            output = new PrintWriter(socket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            System.out.println("SocketView.18");
            throw e;
        }      
    }
    public String getLine() throws Exception {
        try {
            String text = input.readLine();
            if (text != null) {
                return text;
            }
            throw new ServerErrorException();
        } catch (Exception e) {
            System.out.println("SocketView.30");
            throw e;
        }
    }
    public void sendMoveRequest(String fromXY, String toXY) {
        output.println(fromXY);
        output.println(toXY);
    }
    public void endConnection() throws Exception {
        input.close();
        output.close();
        socket.close();
    }
    public SquareStateEnum[][] getBoard(int size) {
        SquareStateEnum[][] result = new SquareStateEnum[size][size];
        for (int i = 0; i < size; i++) {
            try {
                String line = this.getLine();
                for (int j = 0; j < size && j < line.length(); j++) {
                    int t = Integer.parseInt(line.charAt(j) + "");
                    result[j][i] = SquareStateEnum.values()[t];
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
