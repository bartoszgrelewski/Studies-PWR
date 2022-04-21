import java.awt.*;

/**
 * klasa kwadratu
 */
public class Square {

    /**
     * deklaracja wspolrzednych x i y poczatku kwadratu, szerokosc i wysokosc,,
     * tablicy sasiednich kwadratow, zmiennej ustawiajacej aktywnosc oraz koloru kwadratu
     */
    boolean active = true;
    private final int x;
    private final int y;
    private final int size;
    private Square[] neighbours;
    private Color color;

    /**
     * konstruktor
     * @param x wspolrzedna x poczatku kwadratu
     * @param y wspolrzedna y poczatku kwadratu
     */
    Square (int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    /**
     * konstruktor
     * @param neighbours
     */
    public void setNeighbours(Square[] neighbours) {
        this.neighbours = neighbours;
    }

    /**
     * metoda zwracajaca sasiednie kwadraty
     * @return sasiedni kwadrat
     */
    public Square[] getNeighbours() {
        return neighbours;
    }

    /**
     * metoda zwracajaca kolor
     * @return kolor
     */
    public Color getColor() {
        return color;
    }

    /**
     * metoda sprawdzajaca czy wspolrzedne mieszcza sie w
     * podanym obrebie figury, w tym wypadku beda to kwadraty
     * @param x wspolrzedna x
     * @param y wspolrzedna y
     * @return zwraca typ logiczny
     */
    public boolean contains(int x, int y) {
        return x > this.x && x < this.x + this.size && y > this.y && y < this.y + this.size;
    }

    /**
     * metoda zmieniajaca aktywnosc watku na nieaktywny
     * graficznie zaznacza podany watek zmieniajac
     * kolor kwadratu na czarny
     */
    public void stop() {
        active = false;
        color = Color.BLACK;
    }

    /**
     * metoda rysująca
     * @param g grafika
     */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }

    /**
     * metoda ustawiajaca kolor kwadratu
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
