package pl.tp;

public class TerminalView implements GameView {

    @Override
    public void showMessage(String message) {
        System.out.println("The Game is on.");
    }

    // TODO tu nie powinna być pętla do 8
    @Override
    public void printBoard(SquareStateEnum[][] boardContent) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardContent[i][j] == SquareStateEnum.White) {
                    System.out.print("\033[97;107m   ");
                } else if (boardContent[i][j] == SquareStateEnum.BlackEmpty) {
                    System.out.print("\033[30;40m   ");
                }
                if (boardContent[i][j] == SquareStateEnum.BlackRed) {
                    System.out.print("\033[91;40m 0 ");
                } else if (boardContent[i][j] == SquareStateEnum.BlackWhite) {
                    System.out.print("\033[97;40m 0 ");
                }
            }

            System.out.print("\033[0m\n");
        }

    }

}
