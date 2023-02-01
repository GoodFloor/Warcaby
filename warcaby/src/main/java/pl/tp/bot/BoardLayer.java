package pl.tp.bot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import pl.tp.SquareStateEnum;

/**
 * Klasa obsługująca wyświetlanie i obsługiwanie planszy
 */
public class BoardLayer extends JPanel implements ActionListener {
    private int size;
    private ImageIcon white;
    private ImageIcon blackEmpty;
    private ImageIcon blackRed;
    private ImageIcon blackWhite;
    private ImageIcon blackRedQueen;
    private ImageIcon blackWhiteQueen;
    private WindowView parent;

    /**
     * Konstruktor nowej warstwy wyświetlającej planszę
     * 
     * @param parent Rodzic - WindowView któremu będziemy przekazywać który pionek
     *               został wybrany
     */
    public BoardLayer(WindowView parent) {
        size = 0;
        this.parent = parent;
        white = new ImageIcon(getClass().getResource("textures/white.png"));
        blackEmpty = new ImageIcon(getClass().getResource("textures/blackEmpty.png"));
        blackRed = new ImageIcon(getClass().getResource("textures/blackRed.png"));
        blackWhite = new ImageIcon(getClass().getResource("textures/blackWhite.png"));
        blackRedQueen = new ImageIcon(getClass().getResource("textures/blackRedQueen.png"));
        blackWhiteQueen = new ImageIcon(getClass().getResource("textures/blackWhiteQueen.png"));
    }

    /**
     * Metoda usuwająca całą zawartość planszy i zapisująca nowy rozmiar planszy
     * 
     * @param size Ilość pól na jednym boku planszy
     */
    public void drawNew(int size) {
        this.removeAll();
        this.size = size;
    }

    /**
     * Metoda renderująca planszę na podstawie argumentu
     * 
     * @param board Tablica ze statusem każdego pola na planszy
     */
    public void renderBoard(SquareStateEnum[][] board) {
        this.removeAll();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ImageIcon texture;
                if (SquareStateEnum.BlackEmpty == board[i][j]) {
                    texture = blackEmpty;
                } else if (SquareStateEnum.BlackRed == board[i][j]) {
                    texture = blackRed;
                } else if (SquareStateEnum.BlackWhite == board[i][j]) {
                    texture = blackWhite;
                } else if (SquareStateEnum.BlackRedQueen == board[i][j]) {
                    texture = blackRedQueen;
                } else if (SquareStateEnum.BlackWhiteQueen == board[i][j]) {
                    texture = blackWhiteQueen;
                } else {
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
        parent.addToBuffer(arg0.getActionCommand());
    }

    /**
     * Funkcja zwracająca obecnie przechowywany rozmiar planszy
     * 
     * @return Ilość pól na jednym boku planszy
     */
    public int getBoardSize() {
        return size;
    }

}
