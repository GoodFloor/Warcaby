package pl.tp.server;

import pl.tp.SquareStateEnum;

import java.util.Scanner;

/**
 * Klasa implementująca podstawowy widok terminalowy
 */
public class TerminalView implements GameView {

    private Scanner scanner;

    @Override
    public void end() {
        this.scanner.close();
    }

    @Override
    public void start() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    // TODO tu nie powinna być pętla do 8
    @Override
    public void printBoard(SquareStateEnum[][] boardContent) {
        int size = boardContent.length;
        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.printf(" %c ", (char) (i + 65));
        }
        System.out.print("\n");

        for (int i = 0; i < size; i++) {

            System.out.printf(" %d ", (size - i));

            for (int j = 0; j < size; j++) {
                if (boardContent[i][j] == SquareStateEnum.White) {
                    System.out.print("\033[97;107m   ");
                } else if (boardContent[i][j] == SquareStateEnum.BlackEmpty) {
                    System.out.print("\033[30;40m   ");
                }
                if (boardContent[i][j] == SquareStateEnum.BlackRed) {
                    System.out.print("\033[91;40m 0 ");
                } else if (boardContent[i][j] == SquareStateEnum.BlackWhite) {
                    System.out.print("\033[97;40m 0 ");
                } else if (boardContent[i][j] == SquareStateEnum.BlackRedQueen) {
                    System.out.print("\033[91;40m H ");
                } else if (boardContent[i][j] == SquareStateEnum.BlackWhiteQueen) {
                    System.out.print("\033[97;40m H ");
                }
            }

            System.out.printf("\033[0m %d \n", (size - i));
        }

        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.printf(" %c ", (char) (i + 65));
        }
        System.out.print("\n");

    }

    @Override
    public String[] getMove() {
        String result[] = new String[2];

        try {

            System.out.println("Wybierz pionek do ruchu:");
            result[0] = scanner.nextLine();
            System.out.println("Wybierz miejsce do postawienia pionka:");
            result[1] = scanner.nextLine();

        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    @Override
    public void printException(Exception e) {
        System.out.println(e);
    }

}
