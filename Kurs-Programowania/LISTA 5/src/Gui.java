import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * klasa okna
 */
public class Gui extends JFrame {

    /**
     * deklaracja: listy kwadratow, wielkosci pojedynczego kwadratu i przestrzeni
     */
    private final ArrayList<Square> squareList = new ArrayList<>();
    private final int SIZE = 30;
    Surface surface;

    /**
     * konstruktor okna
     * @param n - liczba kolumn
     * @param m - liczba wierszy
     * @param k - szybkosc zmiany
     * @param p - prawdopodobienstwo
     */
    public Gui(int n, int m, int k, double p) {
        initSquares(n, m);
        initSurface();
        initUI(n, m);
        setNeighbours(n);
        startThreads(k,p);
    }

    /**
     * metoda startujaca watki
     * @param k szybkosc zmiany
     * @param p prawdopodobienstwo
     */
    private void startThreads(int k, double p) {
        for(Square square:squareList) {
            Thread thread = new SquareThread(square,surface,k,p);
            thread.start();
        }
    }

    /**
     * metoda dodajaca przestrzen do okna
     */
    private void initSurface() {
        surface = new Surface(squareList);
        add(surface);
    }

    /**
     * metoda operujaca sasiednimi kwadratami
     * @param n liczba kolumn
     */
    private void setNeighbours(int n) {

        for(int i = 0; i < squareList.size(); i++) {
            Square[] neighbours = new Square[4];

            if((i - n) >= 0) {
                neighbours[0] = squareList.get(i - n);
            } else {
                neighbours[0] = squareList.get(squareList.size() - n + i);
            }
            if((i + n) < squareList.size()) {
                neighbours[1] = squareList.get(i + n);
            } else {
                neighbours[1] = squareList.get(squareList.size() + n - i);
            }
            if(i % n != 0) {
                neighbours[2] = squareList.get(i - 1);
            } else {
                neighbours[2] = squareList.get(i + n - 1);
            }
            if(i % n != n-1) {
                neighbours[3] = squareList.get(i + 1);
            } else {
                neighbours[3] = squareList.get(i - n + 1);
            }
            squareList.get(i).setNeighbours(neighbours);
        }
    }

    /**
     * metoda wywolujaca kwadraty
     * @param n ilosc kolumn
     * @param m ilosc wierszy
     */
    private void initSquares(int n, int m) {
        int y = 0, x = 0;
        while (y < m) {
            Square square = new Square(x * SIZE, y * SIZE, SIZE);
            square.setColor(new Color((int) (Math.random() * 255),
                    (int) (Math.random() * 255), (int) (Math.random() * 255)));
            squareList.add(square);
            x++;
            if (x == n) {
                x = 0;
                y++;
            }
        }
    }

    /**
     * initUI dodaje przestrzen do okna oraz implementuje jego wielkosc z pozostalymi zmiennymi
     */
    private void initUI(int n, int m) {

        setTitle("Symulacja - Bartosz Grelewski");
        setSize(n * SIZE,m * SIZE + 20);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
