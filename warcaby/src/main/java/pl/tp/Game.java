package pl.tp;

public class Game implements GameInterface {
    GameVersion gameVersion;

    public void run() {
        gameVersion.run();
    }

    @Override
    public void setGameVersion(int versionNr) {
        switch (versionNr) {
            case 1:
                this.gameVersion = new BasicVersion();
        }
    }

}
