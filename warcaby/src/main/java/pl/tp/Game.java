package pl.tp;

public class Game {
    GameVersion gameVersion;

    public Game(int versionNr) {

        switch (versionNr) {
            case 1:
                this.gameVersion = new BasicVersion();
        }

    }

    public void run() {
        gameVersion.run();
    }

}
