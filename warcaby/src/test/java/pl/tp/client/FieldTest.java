package pl.tp.client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class FieldTest {

    @Test
    public void fieldSetActionTest1() {
        BoardLayer boardLayer = new BoardLayer(null);
        boardLayer.drawNew(8);
        Field testField = new Field(5, 5, boardLayer);
        assertEquals("F3", testField.getActionCommand());
    }
    @Test
    public void fieldSetActionTest2() {
        BoardLayer boardLayer = new BoardLayer(null);
        boardLayer.drawNew(8);
        Field testField = new Field(1, 0, boardLayer);
        assertEquals("B8", testField.getActionCommand());
    }
}
