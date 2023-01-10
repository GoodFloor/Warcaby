package pl.tp.server;

/**
 * Klasa abstrakcyjna rodzaju pionka (zwykły / dama)
 * 
 * Stan (abstrakcyjny) we wzorcu state
 */
public abstract class AbstractPieceState {
    protected boolean isStartingAtBottom;
    protected String stateName;

    /**
     * Pobieranie informacji czy dany ruch jest możliwy
     * 
     * @param posX1 początkowa pozycja x
     * @param posY1 początkowa pozycja y
     * @param posX2 docelowa pozycja x
     * @param posY2 docelowa pozycja y
     * 
     * @return Zwraca tablicę możliwych ruchów
     * @throws IncorrectPositionException Zwraca błąd w przypadku podania
     *                                    niepoprawnych pozycji
     */
    public abstract int[][] canGoTo(int posX1, int posY1, int posX2, int posY2) throws IncorrectPositionException;

    public String getState() {
        return stateName;
    }

    /**
     * Zakodowanie informacji o pozycji pionka
     * 
     * @param posX numer pozycji x
     * @param posY numer pozycji y
     * @return zakodowana pozycja
     */
    protected String codePosition(int posX, int posY) {
        String result = "";
        result += (char) (posX + 65);
        result += posY + 1;
        return result;
    }

    /**
     * Ustawieni informacji o stronie z której rozpoczyna grę pionek
     * 
     * @param isStartingAtBottom prawda, jeśli zaczyna na dole planszy
     */
    public void setStartingAtBottom(boolean isStartingAtBottom) {
        this.isStartingAtBottom = isStartingAtBottom;
    }
    public boolean getIsStartingAtBottom() {
        return this.isStartingAtBottom;
    }
}
