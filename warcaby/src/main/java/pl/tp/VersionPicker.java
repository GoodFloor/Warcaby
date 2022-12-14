package pl.tp;

public class VersionPicker implements VersionFactoryInterface {
    @Override
    public GameVersion getGame(int versionNr) {
        switch (versionNr) {
            case 1:
                return new BasicVersion();
            default:
                return null;
        }
    }

}
