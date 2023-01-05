package pl.tp.client;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Field extends Rectangle{
    Field(double width, double height, int color) {
        super(width, height);
        if (color == 1) {
            setFill(Color.YELLOWGREEN);
        }
        else {
            setFill(Color.LIGHTYELLOW);
        }
        setStroke(Color.BLACK);
    }
}
