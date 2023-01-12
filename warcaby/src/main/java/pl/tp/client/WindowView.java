package pl.tp.client;

import java.awt.*;
import java.awt.event.*;

import pl.tp.SquareStateEnum;

public class WindowView extends Frame implements View{
    // private Pane root;
    private BoardLayer board;
    private PiecesLayer pieces;
    // private Pane userInterface;
    //private Stage mainStage;
    //private int boardWidth;
    public WindowView(int boardSize) {
        pieces = new PiecesLayer(boardSize);
        board = new BoardLayer(boardSize);
        add(pieces);
        add(board);
        setSize(boardSize * 100, boardSize * 100);
        setTitle("Warcaby");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    public void updatePieces(SquareStateEnum[][] currentPositions) {
        pieces.drawPieces(currentPositions);
    }
    public void updateBoard(int boardSize) {
        board.removeAll();
        board = new BoardLayer(boardSize);
        add(board);
    }
    @Override
    public void end() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public String[] getMove() {
        // TODO Auto-generated method stub
        return new String[2];
    }
    @Override
    public void printMessage(String message) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void printException(Exception e) {
        // TODO Auto-generated method stub
        
    }
}
