package pl.tp.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import pl.tp.SquareStateEnum;

public class BoardLayer extends JPanel implements ActionListener {
    private int size;
    private ImageIcon white;
    private ImageIcon blackEmpty;
    private ImageIcon blackRed;
    private ImageIcon blackWhite;
    private ImageIcon blackRedQueen;
    private ImageIcon blackWhiteQueen;
    private String[] movesBuffer;
    private boolean isMyMove;
    private int howManyMovesBuffered;


    public BoardLayer() {
        size = 0;
        white = new ImageIcon("/home/goodfloor/Obrazy/Warcaby/white.png");
        blackEmpty = new ImageIcon("/home/goodfloor/Obrazy/Warcaby/blackEmpty.png");
        blackRed = new ImageIcon("/home/goodfloor/Obrazy/Warcaby/blackRed.png");
        blackWhite = new ImageIcon("/home/goodfloor/Obrazy/Warcaby/blackWhite.png");
        blackRedQueen = new ImageIcon("/home/goodfloor/Obrazy/Warcaby/blackRedQueen.png");
        blackWhiteQueen = new ImageIcon("/home/goodfloor/Obrazy/Warcaby/blackWhiteQueen.png");
        movesBuffer = new String[2];
        isMyMove = false;
        howManyMovesBuffered = 0;
    }

    public void drawNew(int size) {
        this.removeAll();
        this.size = size;
        movesBuffer = new String[2];
        isMyMove = false;
        howManyMovesBuffered = 0;
    }
    public void renderBoard(SquareStateEnum[][] board) {
        this.removeAll();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ImageIcon texture;
                if (SquareStateEnum.BlackEmpty == board[i][j]) {
                    texture = blackEmpty;
                }
                else if (SquareStateEnum.BlackRed == board[i][j]) {
                    texture = blackRed;
                }
                else if (SquareStateEnum.BlackWhite == board[i][j]) {
                    texture = blackWhite;
                }
                else if (SquareStateEnum.BlackRedQueen == board[i][j]) {
                    texture = blackRedQueen;
                }
                else if (SquareStateEnum.BlackWhiteQueen == board[i][j]) {
                    texture = blackWhiteQueen;
                }
                else {
                    texture = white;
                }
                Field field = new Field(j, i, this);
                field.setIcon(texture);
                field.addActionListener(this);
                this.add(field);
            }
        }
        this.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(isMyMove && howManyMovesBuffered < 2) {
            movesBuffer[howManyMovesBuffered] = arg0.getActionCommand();
            howManyMovesBuffered++;
        }
    }
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
    public void disableMove() {
        isMyMove = false;
    }
    public int getBoardSize() {
        return size;
    }

}
