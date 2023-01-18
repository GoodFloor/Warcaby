package pl.tp.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;

import pl.tp.SocketCommandsEnum;
import pl.tp.SquareStateEnum;

/**
 * Klasa konkretna implementująca interfejs View oraz obsługująca widok związany z oknem awt 
 */
public class WindowView extends Frame implements View{
    private BoardLayer board;
    private Label message;
    private JButton drawProposal;
    private JButton acceptDrawBtn;
    private JButton rejectDrawBtn;
    private String[] movesBuffer;
    private int howManyMovesBuffered;
    private boolean isMyMove;
    private boolean isDrawResponseSet;
    private boolean drawResponse;
    private SquareStateEnum[][] lastKnownBoardState;
    private boolean exited;
    /**
     * Domyślny konstruktor ustawiający podstawowe parametry takie jak rozmiar i zawartość okna
     */
    public WindowView() {
        exited = false;
        isMyMove = false;
        movesBuffer = new String[2];
        howManyMovesBuffered = 0;
        isDrawResponseSet = false;
        drawResponse = false;
        setSize(200, 50);
        setTitle("Warcaby");
        setLayout(null);
        setVisible(true);
        board = new BoardLayer(this);
        board.setBounds(0, 50, 200, 100);
        message = new Label("Oczekiwanie na serwer");
        message.setBounds(0, 25, 200, 25);
        drawProposal = new JButton("Zaproponuj remis");
        drawProposal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                proposeDraw();                
            }
        });
        acceptDrawBtn = new JButton("Tak");
        acceptDrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                acceptDraw();
            }
        });
        rejectDrawBtn = new JButton("Nie");
        rejectDrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                rejectDraw();
            }
        });
        add(acceptDrawBtn);
        add(rejectDrawBtn);
        acceptDrawBtn.setVisible(false);
        rejectDrawBtn.setVisible(false);
        add(drawProposal);
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
        exited = true;
        howManyMovesBuffered = 2;
        dispose();        
    }

    @Override
    public void newBoard(int size) {
        message.setText("Tworzenie nowej gry");
        setSize(size * 50, size * 50 + 50);    
        board.setBounds(0, 50, size * 50, size * 50);
        board.drawNew(size);
        message.setBounds(0, 25, size * 25, 25);
        drawProposal.setBounds(size * 25, 25, size * 25, 25);
        acceptDrawBtn.setBounds(0, 75, size * 25, 50);
        rejectDrawBtn.setBounds(size * 25, 75, size * 25, 50);
    }

    @Override
    public void drawBoard(SquareStateEnum[][] pieces) {
        lastKnownBoardState = pieces;
        board.renderBoard(pieces);
    }

    @Override
    public void printMessage(String message) {
        this.message.setText(message);
    }

    @Override
    public String[] getMove() {
        isMyMove = true;
        while (true) {
            if (howManyMovesBuffered == 2) {
                howManyMovesBuffered = 0;
                return movesBuffer;
            }
            else {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void endMove() {
        isMyMove = false;
    }
    public void addToBuffer(String position) {
        if(isMyMove) {
            movesBuffer[howManyMovesBuffered] = position;
            howManyMovesBuffered ++;
            if (howManyMovesBuffered == 1) {
                this.printMessage(position + "->");
            }
            else if (howManyMovesBuffered == 2) {
                this.printMessage(movesBuffer[0] + "->" + position);
                this.endMove();
            }
        }
    }
    /**
     * Obsługa wciśnięcia przycisku do proponowania remisu
     */
    public void proposeDraw() {
        if (isMyMove) {
            movesBuffer[0] = SocketCommandsEnum.proposeDraw.toString();
            movesBuffer[1] = null;
            howManyMovesBuffered = 2;
        }
    }
    @Override
    public void drawProposed() {
        acceptDrawBtn.setVisible(true);
        rejectDrawBtn.setVisible(true);
        board.setVisible(false);
        repaint();
    }
    @Override
    public boolean getDrawResponse() {
        while (!isDrawResponseSet) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        isDrawResponseSet = false;
        acceptDrawBtn.setVisible(false);
        rejectDrawBtn.setVisible(false);
        board.setVisible(true);
        this.drawBoard(lastKnownBoardState);
        repaint();
        return drawResponse;

    }
    @Override
    public void endGame(String message) {
        this.endMove();
        this.printMessage(message);
    }
    /**
     * Obsługa przycisku odrzucającego propozycję remisu
     */
    private void rejectDraw() {
        drawResponse = false;
        isDrawResponseSet = true; 
        
    }
    /**
     * Obsługa przycisku akceptującego propozycję remisu
     */
    private void acceptDraw() {
        drawResponse = true;
        isDrawResponseSet = true;        
    }

    @Override
    public boolean isExited() {
        return exited;
    }
}
