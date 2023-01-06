package pl.tp.client;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class PiecesLayer extends GridPane{
    Piece[][] piecesMatrix;
    double pieceRadius = 40;
    Insets piecesMargin = new Insets(10, 10, 10, 10);
    public void drawNew(int width, int height) {
        piecesMatrix = new Piece[width][height];
        this.setGridLinesVisible(true);
        for (int i = 0; i < height; i++) {
            this.add(new Rectangle(0, 100), 0, i);
            for (int j = 0; j < width; j++) {
                if (i == 0) {
                    this.add(new Rectangle(100, 0), j, i);
                }
                if(i == 3 || i == 4 || (i + j) % 2 == 0) {
                    piecesMatrix[j][i] = null;
                }
                else if(i < 3){
                    piecesMatrix[j][i] = new Piece(pieceRadius, 1);
                    this.add(piecesMatrix[j][i], j, i);
                    PiecesLayer.setMargin(piecesMatrix[j][i], piecesMargin);
                }
                else {
                    piecesMatrix[j][i] = new Piece(pieceRadius, 0);
                    this.add(piecesMatrix[j][i], j, i);
                    PiecesLayer.setMargin(piecesMatrix[j][i], piecesMargin);
                }
            }
        }
    }
    public void switchPlaces(int posX1, int posY1, int posX2, int posY2) {
        if(piecesMatrix[posX1][posY1] != null) {
            this.getChildren().remove(piecesMatrix[posX1][posY1]);
        }
        if(piecesMatrix[posX2][posY2] != null) {
            this.getChildren().remove(piecesMatrix[posX2][posY2]);
        }
        Piece tempPiece = piecesMatrix[posX1][posY1];
        piecesMatrix[posX1][posY1] = piecesMatrix[posX2][posY2];
        piecesMatrix[posX2][posY2] = tempPiece;
        if(piecesMatrix[posX1][posY1] != null) {
            this.add(piecesMatrix[posX1][posY1], posX1, posY1);
        }
        if(piecesMatrix[posX2][posY2] != null) {
            this.add(piecesMatrix[posX2][posY2], posX2, posY2);
        }
    }
    public void killPiece(int posX, int posY) {
        if (piecesMatrix[posX][posY] != null) {
            this.getChildren().remove(piecesMatrix[posX][posY]);
            piecesMatrix[posX][posY] = null;
        }
    }
}
