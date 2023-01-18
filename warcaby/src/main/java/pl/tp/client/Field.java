package pl.tp.client;

import javax.swing.JButton;

/**
 * Klasa pola na planszy
 */
public class Field extends JButton {
    final BoardLayer parent;
    /**
     * Konstruktor ustawiający rozmiar, i pozycję na planszy
     * @param posX które to pole od lewej
     * @param posY które to pole od góry
     * @param parent rodzic - JPanel
     */
    public Field(int posX, int posY, BoardLayer parent) {
        this.parent = parent;
        this.setBounds(posX * 50, posY * 50, 50, 50);
        int maxXY = parent.getBoardSize();

        String y = Integer.toString(maxXY - posY);
        char x = (char)(posX + 65);
        String position = x + "" + y;
        this.setActionCommand(position);
    }
}
