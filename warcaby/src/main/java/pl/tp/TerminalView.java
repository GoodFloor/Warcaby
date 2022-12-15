package pl.tp;

public class TerminalView implements GameView {

    @Override
    public void showMessage(String message) {
        System.out.println("The Game is on.");
    }

    // TODO tu nie powinna być pętla do 8
    @Override
    public void printBoard(SquareStateEnum[][] boardContent) {
        System.out.print("   ");
        for (int i = 0; i < 8; i++) {
            System.out.printf(" %c ", (char) (i + 65));
        }
        System.out.print("\n");

        for (int i = 0; i < 8; i++) {

            System.out.printf(" %d ", (8 - i));

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

            System.out.printf("\033[0m %d \n", (8 - i));
        }

        System.out.print("   ");
        for (int i = 0; i < 8; i++) {
            System.out.printf(" %c ", (char) (i + 65));
        }
        System.out.print("\n");

    }

}
