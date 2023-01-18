package pl.tp.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import pl.tp.SocketCommandsEnum;
import pl.tp.SquareStateEnum;

/**
 * Klasa obsługująca połączenie klienta z serwerem - View w modelu MVC
 */
public class ServerView {
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    /**
     * Konstruktor ustawiający podstawowe parametry połączenia z serwerem
     */
    public ServerView() {
        try {
            socket = new Socket("localhost", 4444);
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Metoda odczytująca polecenie wysłane przez serwer
     * @return Polecenie do wykonania
     * @throws ServerDownException Wyjątek jeżeli serwer został zamknięty
     * @throws Exception Wyjątek przy nieudanej próbie odczytu linii z bufora
     */
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
    /**
     * Metoda wysyłająca serwerowi prośbę o przesunięcie pionka
     * @param sourceXY Pozycja źródłowa zakodowana polami na planszy(np. A6)
     * @param destinationXY Pozycja docelowa pionka
     */
    public void movePiece(String sourceXY, String destinationXY) {
        output.println(sourceXY);
        output.println(destinationXY);
    }
    /**
     * Metoda zamykająca połączenie z serwerem
     */
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
    /**
     * Metoda pomocnicza do odczytu obecnego stanu planszy - zamienia linię ordynałów na jednowymiarową tablicę enumów z zakodowanymi odpowiednimi stanami planszy
     * @param line Linia zawierająca liczby porządkowe enumeracji odczytana z serwera
     * @return Tablica enumów ze stanami poszczególnych pól w tym rzędzie
     */
    private SquareStateEnum[] textToEnum(String line) {
        int size = line.length();
        SquareStateEnum[] result = new SquareStateEnum[size];
        for (int i = 0; i < size; i++) {
            int currEnum = Integer.parseInt(line.charAt(i) + "");
            result[i] = SquareStateEnum.values()[currEnum];
        }
        return result;
    }
    /**
     * Funkcja odczytująca stan planszy z serwera
     * @return Tablica dwuwymiarowa z zakodowanymi polami na planszy i ich stanami
     * @throws Exception Wyjątek zwracany w przypadku błędu odczytu linii
     */
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
    /**
     * Funkcja zwracająca do serwera odpowiedź na zapytanie o uznanie remisu
     * @param isAccepted Czy użytkownik zgodził się na remis
     */
    public void sendDrawResponse(boolean isAccepted) {
        if (isAccepted) {
            output.println(SocketCommandsEnum.acceptDraw.toString());
        } else {
            output.println(SocketCommandsEnum.rejectDraw.toString());            
        }
    }

}
