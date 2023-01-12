package pl.tp.client;

import java.awt.*;

public class BoardLayer extends Panel{
    public BoardLayer(int size) {
        setLayout(new GridLayout(size, size));
        for (int i = 0; i < size * size; i++) {
            add(new Button());
        }
    }
    // public void drawNew(int width, int height) {
    //     double fieldWidth = 800 / width;
    //     double fieldHeight = 800 / height;
    //     for (int i = 0; i < height; i++) {
    //         for (int j = 0; j < width; j++) {
    //             this.add(new Field(fieldWidth, fieldHeight, (i + j) % 2), j, i);
    //         }
    //     }
    // }
}
