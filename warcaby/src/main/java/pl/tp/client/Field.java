package pl.tp.client;

import javax.swing.JButton;

public class Field extends JButton {
    final BoardLayer parent;
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
