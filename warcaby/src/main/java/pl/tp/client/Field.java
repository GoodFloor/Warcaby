package pl.tp.client;

import javax.swing.JButton;

public class Field extends JButton {
    public Field(int posX, int posY) {
        this.setBounds(posX * 50, posY * 50, 50, 50);
    }
}
