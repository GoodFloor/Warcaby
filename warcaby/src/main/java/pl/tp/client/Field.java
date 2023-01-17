package pl.tp.client;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import pl.tp.SquareStateEnum;

public class Field extends JButton {
    public Field(SquareStateEnum type) {
        ImageIcon texture = new ImageIcon("White.png");
        this.setIcon(texture);
    }
}
