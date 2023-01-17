package pl.tp.client;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import pl.tp.SquareStateEnum;

public class WindowView extends Frame implements View{
    BoardLayer board;
    Panel pieces;
    Label message;
    public WindowView() {
        setSize(200, 50);
        setTitle("Warcaby");
        setLayout(null);
        setVisible(true);
        board = new BoardLayer();
        board.setBounds(0, 50, 200, 100);
        pieces = new Panel();
        pieces.setBounds(0, 50, 200, 100);
        pieces.setBackground(Color.green);
        message = new Label("Oczekiwanie na serwer");
        message.setBounds(0, 25, 200, 25);
        add(message);
        //add(pieces);
        add(board);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                close();
            }
        });
    }

    @Override
    public void close() {
        dispose();        
    }

    @Override
    public void newBoard(int size) {
        message.setText("Tworzenie nowej gry");
        setSize(size * 50, size * 50 + 50);    
        board.setBounds(0, 50, size * 50, size * 50);
        board.drawNew(size);
        pieces.setBounds(0, 50, size * 50, size * 50);
        message.setBounds(0, 25, size * 50, 25);
    }

    @Override
    public void drawBoard(SquareStateEnum[][] pieces) {
        board.renderBoard(pieces);
    }

    @Override
    public void printMessage(String message) {
        this.message.setText(message);
    }
    
}
