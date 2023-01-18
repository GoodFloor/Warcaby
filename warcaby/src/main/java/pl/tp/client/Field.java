package pl.tp.client;

import javax.swing.JButton;

public class Field extends JButton {
    final BoardLayer parent;
    public Field(int posX, int posY, BoardLayer parent) {
        this.parent = parent;
        this.setBounds(posX * 50, posY * 50, 50, 50);
        char x = (char)(posX + 1 + 48);
        char y = (char)(parent.getBoardSize() - posY - 1 + 65);
        String position = y + "" + x;
        this.setActionCommand(position);
    }
}
