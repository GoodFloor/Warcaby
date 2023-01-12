package pl.tp.client;

import java.awt.*;

import pl.tp.SquareStateEnum;

public class PiecesLayer extends Panel{
    private int size;
    public PiecesLayer(int boardSize) {
        this.size = boardSize;
    }    
    public void drawPieces(SquareStateEnum[][] pieces) {
        this.removeAll();
        this.setLayout(new GridLayout(size, size));
        for (int i = 0; i < pieces.length; i++) {
            //this.add(new Rectangle(0, 100), 0, i);
            for (int j = 0; j < pieces[i].length; j++) {
                System.out.print(pieces[j][i]);
                // if (i == 0) {
                //     this.add(new Rectangle(100, 0), j, i);
                // }
                // if(i == 3 || i == 4 || (i + j) % 2 == 0) {
                //     piecesMatrix[j][i] = null;
                // }
                // else if(i < 3){
                //     piecesMatrix[j][i] = new Piece(pieceRadius, 1);
                //     this.add(piecesMatrix[j][i], j, i);
                //     PiecesLayer.setMargin(piecesMatrix[j][i], piecesMargin);
                // }
                // else {
                //     piecesMatrix[j][i] = new Piece(pieceRadius, 0);
                //     this.add(piecesMatrix[j][i], j, i);
                //     PiecesLayer.setMargin(piecesMatrix[j][i], piecesMargin);
                // }
            }
            System.out.printf("\n");
        }
    }
}
