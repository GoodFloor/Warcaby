package pl.tp.server;

/**
 * Twórca konkretny we wzorcu Factory (jedyny)
 */
public class VersionFactory implements VersionFactoryInterface {
    @Override
    public GameController getGame(int versionNr) {
        switch (versionNr) {
            case 1:
                return new ClassicGameController();
            case 2:
                return new EnglishGameController();
            case 3:
                return new RussianGameController();
            case 4:
                return new PolishGameController();
            default:
                return null;
        }
    }

}
