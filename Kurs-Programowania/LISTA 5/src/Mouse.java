import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * klasa przycisku myszy
 */
public class Mouse extends MouseAdapter {


    private final ArrayList<Square> squares;

    /**
     * konstruktor metody Mouse
     * @param squares
     */
    Mouse(ArrayList<Square> squares) {
        this.squares = squares;
    }

    /**
     * metoda wywolujaca metode "stop" w wypadku
     * najechania myszka na kwadrat i klikniecia, nastepnie jego sprawdzenie
     * metoda "contains" czy znajduje sie w jego obrebie
     * @param e przechwyt przycisku
     */
    public void mouseClicked(MouseEvent e) {
        for (Square square : squares)
            if (square.contains(e.getX(), e.getY()))
                square.stop();
    }
}