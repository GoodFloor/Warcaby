package pl.tp.client;

import java.util.Scanner;
import pl.tp.SquareStateEnum;

public class TerminalView implements View{
    private int boardSize;
    private Scanner scanner;
    public TerminalView(int boardSize) {
        this.boardSize = boardSize;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void updatePieces(SquareStateEnum[][] boardContent) {
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
    public void end() {
        this.scanner.close();
    }
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
    @Override
    public void printException(Exception e) {
        System.out.print("\033[91;40m");
        e.printStackTrace();
        System.out.print("\033[0m");
    }

}  
