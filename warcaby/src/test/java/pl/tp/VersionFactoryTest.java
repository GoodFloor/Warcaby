package pl.tp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import pl.tp.server.GameController;
import pl.tp.server.VersionFactory;
import pl.tp.server.VersionFactoryInterface;

public class VersionFactoryTest {

    @Test
    public void selectEnglishVersionTest() {
        VersionFactoryInterface factory = new VersionFactory();
        GameController game = factory.getGame(1);

        assertEquals(game.getClass().getName(), "pl.tp.server.EnglishGameController");
    }

    @Test
    public void selectRussianVersionTest() {
        VersionFactoryInterface factory = new VersionFactory();
        GameController game = factory.getGame(2);

        assertEquals(game.getClass().getName(), "pl.tp.server.RussianGameController");
    }

    @Test
    public void selectPolishVersionTest() {
        VersionFactoryInterface factory = new VersionFactory();
        GameController game = factory.getGame(3);

        assertEquals(game.getClass().getName(), "pl.tp.server.PolishGameController");
    }

    @Test
    public void selectDifferentVersionTest() {
        VersionFactoryInterface factory = new VersionFactory();
        GameController game = factory.getGame(21);

        assertEquals(game, null);

    }
}
