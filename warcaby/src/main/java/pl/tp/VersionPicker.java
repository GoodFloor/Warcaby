package pl.tp;

public class VersionPicker implements VersionFactoryInterface {
    @Override
    public GameController getGame(int versionNr) {
        switch (versionNr) {
            case 1:
                return new ClassicGameController();
            default:
                return null;
        }
    }

}
