import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * klasa przestrzeni
 */
public class Surface extends JPanel {

    /**
     * deklaracja wszystkich kwadratow, aktualnie dodawanego kwadratu
     */

    ArrayList<Square> squareList;

    /**
     * konstruktor przestrzeni
     * @param squareList - lista kwadratow
     */
    Surface(ArrayList<Square> squareList) {
        this.squareList = squareList;
        addMouseListener(new Mouse(squareList));
    }

    /**
     * metoda rysujaca figury
     * @param g grafika
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Square p:squareList)
            p.draw(g);
    }
}