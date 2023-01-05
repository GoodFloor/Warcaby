package pl.tp.client;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class PiecesLayer extends GridPane{
    Piece[][] piecesMatrix;
    double pieceRadius = 40;
    Insets piecesMargin = new Insets(10, 10, 10, 10);
    public void drawNew(int width, int height) {
        piecesMatrix = new Piece[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(i == 3 || i == 4 || (i + j) % 2 == 0) {
                    piecesMatrix[i][j] = null;
                }
                else if(i < 3){
                    piecesMatrix[i][j] = new Piece(pieceRadius, 1);
                    this.add(piecesMatrix[i][j], j, i);
                    PiecesLayer.setMargin(piecesMatrix[i][j], piecesMargin);
                }
                else {
                    piecesMatrix[i][j] = new Piece(pieceRadius, 0);
                    System.out.println(i);
                    this.add(piecesMatrix[i][j], j, i);
                    PiecesLayer.setMargin(piecesMatrix[i][j], piecesMargin);
                }
            }
        }
        this.add(new Field(0, 100, 0), 0, 3);
        this.add(new Field(0, 100, 0), 0, 4);
    }
}
