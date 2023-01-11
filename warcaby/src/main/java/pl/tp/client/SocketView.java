package pl.tp.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketView {
    public static void main(String[] args) {
        Socket socket;
        PrintWriter output;
        BufferedReader input;
        try {
            socket = new Socket("localhost", 4444);
            output = new PrintWriter(socket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true) {
                String text = input.readLine();
                System.out.println(text);
                if("EXT".equals(text)) {
                    break;
                }
            }
            input.close();
            output.close();
            socket.close();
        } catch (Exception e) {
        }      
    }
}
