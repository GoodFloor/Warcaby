package pl.tp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ClassicBoardControllerTest {

    @Test
    public void canKill() {
        ClassicBoardController testSubject = new ClassicBoardController();
        testSubject.resetBoard();

        assertTrue(testSubject.canKill("A1"));
    }
    
}
