package pl.tp.client;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JPanel;

import pl.tp.SquareStateEnum;

public class BoardLayer extends JPanel {
    private int size;
    public void drawNew(int size) {
        this.removeAll();
        this.size = size;
    }
    public void renderBoard(SquareStateEnum[][] board) {
        this.removeAll();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Field field = new Field(board[i][j]);
                field.setBounds(j * 50, i * 50, 50, 50);
                this.add(field);
            }
        }
        this.repaint();
    }


    // public void drawRectangles(Graphics g) {
    //     System.out.println("drawRectangles");
    //     Graphics2D g2d = (Graphics2D) g;
    //     int size = this.getWidth() / 50;
    //     for (int i = 0; i < size; i++) {
    //         for (int j = 0; j < size; j++) {
    //             if ((i + j) % 2 == 0) {
    //                 g2d.setColor(new Color(255, 255, 153));
    //             }
    //             else {
    //                 g2d.setColor(new Color(52, 94, 0));
    //             }
    //             g2d.fillRect(i * 50, j * 50, 50, 50);
    //         }
    //     }
    // }
    // @Override
    // public void paint(Graphics g) {
    //     System.out.println("paint");
    //     //super.paint(g);
    //     drawRectangles(g);
    // }
}
