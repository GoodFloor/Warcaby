package pl.tp.client;

import javafx.scene.layout.GridPane;

public class BoardLayer extends GridPane{
    public void drawNew(int width, int height) {
        double fieldWidth = 800 / width;
        double fieldHeight = 800 / height;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.add(new Field(fieldWidth, fieldHeight, (i + j) % 2), i, j);
            }
        }
    }
}
