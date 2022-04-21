import java.awt.*;

/**
 * klasa watku
 */
public class SquareThread extends Thread {

    /**
     * deklaracja szybkosci, prawdopodobienstwa, kwadratu i przestrzeni
     */
    private final int k;
    private final double p;
    private final Square square;
    private final Surface surface;

    /**
     * konstruktor watku
     * @param square obslugiwany kwadrat
     * @param surface przestrzen
     * @param k szybkosc zmiany
     * @param p prawdopodobienstwo zmiany
     */
    SquareThread(Square square, Surface surface, int k, double p) {
        this.square = square;
        this.surface = surface;
        this.k = k;
        this.p = p;
    }

    /**
     * metoda wykonywana przez watek
     */
    @Override
    public void run() {
        while (square.active) {
            setNewColor();
            surface.repaint();
            try {
                sleep((long) (Math.random() * k));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * metoda watku obslugujaca losowanie koloru i jego przypisanie
     */
    synchronized private void setNewColor() {
        if (Math.random() < p) {
            square.setColor(new Color((int) (Math.random() * 255),
                    (int) (Math.random() * 255), (int) (Math.random() * 255)));
        } else {
            int red = 0, green = 0 ,blue = 0, count = 0;

            for (Square neighbour : square.getNeighbours()) {
                if (neighbour.active) {
                    red += neighbour.getColor().getRed();
                    green += neighbour.getColor().getGreen();
                    blue += neighbour.getColor().getBlue();
                    count++;
                }
            }
            if(count > 0) {
                red = red/count;
                green = green/count;
                blue = blue/count;
                square.setColor(new Color(red,green,blue));
            }
        }
    }
}
