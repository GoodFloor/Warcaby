package pl.tp.client;

import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.tp.SquareStateEnum;

public class BoardLayer extends JPanel {
    private int size;
    ImageIcon white;
    ImageIcon blackEmpty;
    ImageIcon blackRed;
    ImageIcon blackWhite;
    ImageIcon blackRedQueen;
    ImageIcon blackWhiteQueen;


    public BoardLayer() {
        size = 0;
        white = new ImageIcon("/home/goodfloor/Obrazy/Warcaby/white.png");
        blackEmpty = new ImageIcon("/home/goodfloor/Obrazy/Warcaby/blackEmpty.png");
        blackRed = new ImageIcon("/home/goodfloor/Obrazy/Warcaby/blackRed.png");
        blackWhite = new ImageIcon("/home/goodfloor/Obrazy/Warcaby/blackWhite.png");
        blackRedQueen = new ImageIcon("/home/goodfloor/Obrazy/Warcaby/blackRedQueen.png");
        blackWhiteQueen = new ImageIcon("/home/goodfloor/Obrazy/Warcaby/blackWhiteQueen.png");
    }

    public void drawNew(int size) {
        this.removeAll();
        this.size = size;
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
                Field field = new Field(j, i);
                field.setIcon(texture);
                this.add(field);
            }
        }
        this.repaint();
    }
}
