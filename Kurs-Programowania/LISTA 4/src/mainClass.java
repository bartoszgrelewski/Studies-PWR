import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;

/** Glowna klasa
 * @author Bartosz Grelewski
 */
public class mainClass {
    /** Glowna funkcja testujaca
     * @param args argumenty
     */
    public static void main(String[] args) {
        Frame test = new Frame();
        test.setVisible(true);
    }
}

/** Klasa tworzaca okno
 */
class Frame extends JFrame {

    /**
     * Deklaracja menu bar, menu, przyciskow w menu,
     * okna dialogowego, przycisku w oknie dialogowym i przestrzeni
     */
    private JMenuBar mainMenu;
    private JMenu shapeMenu, settingsMenu, infoMenu;
    private JMenuItem circleItem, rectangleItem, triangleItem, infoItem, instructionItem, exitItem, saveItem, loadItem, clearButton;
    private JDialog dialog;
    private JButton okButton;
    private Surface surface;

    /**
     * Konstruktor klasy okna
     */
    public Frame() {
        super("Paint");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu();
        surface();
    }

    /**
     * Funkcja dodajaca menu
     */
    private void menu() {
        mainMenu = new JMenuBar();

        shapeMenu = new JMenu("figura");
        mainMenu.add(shapeMenu);

        circleItem = new JMenuItem("okrag");
        shapeMenu.add(circleItem);
        circleItem.addActionListener(this::okrag);

        rectangleItem = new JMenuItem("prostokat");
        shapeMenu.add(rectangleItem);
        rectangleItem.addActionListener(this::prostokat);

        triangleItem = new JMenuItem("trojkat");
        shapeMenu.add(triangleItem);
        triangleItem.addActionListener(this::trojkat);

        infoMenu = new JMenu("info");
        mainMenu.add(infoMenu);

        infoItem = new JMenuItem("info");
        infoMenu.add(infoItem);
        infoItem.addActionListener(this::info);

        instructionItem = new JMenuItem("instrukcja");
        infoMenu.add(instructionItem);
        instructionItem.addActionListener(this::instrukcja);

        settingsMenu = new JMenu("opcje");
        mainMenu.add(settingsMenu);

        saveItem = new JMenuItem("save");
        settingsMenu.add(saveItem);
        saveItem.addActionListener(this::save);

        loadItem = new JMenuItem("load");
        settingsMenu.add(loadItem);
        loadItem.addActionListener(this::load);

        settingsMenu.addSeparator();
        clearButton = new JMenuItem("clear");
        settingsMenu.add(clearButton);
        clearButton.addActionListener(this::clear);

        exitItem = new JMenuItem("exit");
        mainMenu.add(exitItem);
        exitItem.addActionListener(this::exit);

        setJMenuBar(mainMenu);
    }

    /**
     * Funkcja dodajaca przestrzen do rysowania i edytowania figur
     */
    private void surface() {
        surface = new Surface();
        add(surface);

        setSize(1000, 1000);
        setLocationRelativeTo(null);
    }

    /**
     * Metody obslugujace przyciski
     */
    public void okrag(ActionEvent e) { surface.choice = 1; }

    public void prostokat(ActionEvent e) { surface.choice = 2; }

    public void trojkat(ActionEvent e) { surface.choice = 3; }

    public void exit(ActionEvent e) { System.exit(0); }

    /**
     * Metoda obslugujaca przycisk tworzacy okno dialogowe z informacjami
     *
     * @param e przechwyt przycisku
     */
    public void info(ActionEvent e) {
        dialog = new JDialog(this, "informacje", false);
        dialog.setSize(440, 240);
        dialog.setLayout(new GridLayout(6, 1));

        dialog.setLocationRelativeTo(null);

        JLabel napis1 = new JLabel(" Nazwa: Bogaty paint");
        dialog.add(napis1);
        JLabel napis2 = new JLabel(" Autor: Bartosz Grelewski");
        dialog.add(napis2);
        JLabel napis3 = new JLabel("Przeznaczenie: rysowanie i kolorowanie figur geometrycznych.",SwingConstants.CENTER);
        dialog.add(napis3);
        JLabel napis4 = new JLabel();
        dialog.add(napis4);
        JLabel napis5 = new JLabel();
        dialog.add(napis5);
        okButton = new JButton("ok");
        dialog.add(okButton);
        okButton.addActionListener(this::ok);

        dialog.setVisible(true);
    }

    /**
     * Metoda obslugujaca przycisk tworzacy okno dialogowe z instrukcja
     *
     * @param e przechwyt przycisku
     */
    public void instrukcja(ActionEvent e) {
        dialog = new JDialog(this, "instrukcja", false);
        dialog.setSize(340, 400);
        dialog.setLayout(new GridLayout(6, 1));

        dialog.setLocationRelativeTo(null);

        JLabel napis1 = new JLabel();
        dialog.add(napis1);
        JLabel napis2 = new JLabel("<html>Aby narysowac figure wybierz <br/> w menu trojkat lub prostokat lub kolo, kliknij i przeciagnij po przestrzeni. </html>");
        dialog.add(napis2);
        JLabel napis3 = new JLabel("<html>-w celu przeskalowania nakieruj kursor na figure i uzyj scrolla <br/>-aby zmienic kolor figury kliknij PPM na figure i wybierz zmien kolor</html>");
        dialog.add(napis3);
        JLabel napis4 = new JLabel("<html>-program posiada obsluge zapisu i odczytu w opcjach <br/>-aby wyjsc mozna uzyc przycisuku exit</html>");
        dialog.add(napis4);
        JLabel napis5 = new JLabel();
        dialog.add(napis5);
        okButton = new JButton("ok");
        dialog.add(okButton);
        okButton.addActionListener(this::ok);

        dialog.setVisible(true);
    }

    /**
     * Metoda obslugujaca przycisk usuwajacy liste z figurami
     *
     * @param e przechwyt przycisku
     */
    public void clear(ActionEvent e) {
        Surface.shapes.clear();
        repaint();
    }

    /**
     * Metoda obslugujaca przycisk zamykajacy okno dialogowe
     *
     * @param e przechwyt przycisku
     */
    public void ok(ActionEvent e) {
        dialog.setVisible(false);
    }

    /**
     * Deklaracja miejsca na wpisanie nazwy zapisu
     */
    JTextArea save;

    /**
     * Deklaracja okna do wczytywania plikow
     */

    JFileChooser fileChooser;
    BufferedReader br;
    File file;
    int returnVal;
    String currentLine;

    /**
     * Metoda obslugujaca przycisk "Zapisz" zapisujaca informacje o figurach do pliku tekstowego
     * @param e przechwycenie klikniecia w przycisk
     */

    public void save(ActionEvent e) {

        fileChooser = new JFileChooser();
        fileChooser.setSize(400,300);
        fileChooser.setSelectedFile(new File("mojplik.txt"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Plik tekstowy","txt"));

        int wyborUzytkownika = fileChooser.showSaveDialog(this);

        if (wyborUzytkownika == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            String nazwaPliku = file.getAbsolutePath();
            if(!nazwaPliku.endsWith(".txt") ) {
                file = new File(nazwaPliku + ".txt");
            }
            PrintWriter zapis;
            try {
                zapis = new PrintWriter(file);
                for (Shape p : Surface.shapes) {
                    zapis.println(p.nazwa());
                    zapis.println(p.getX1());
                    zapis.println(p.getY1());
                    zapis.println(p.getX2());
                    zapis.println(p.getY2());
                    zapis.println(p.getColor().getRed());
                    zapis.println(p.getColor().getGreen());
                    zapis.println(p.getColor().getBlue());
                }
                zapis.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    /** Metoda obslugujaca przycisk wczytujacy figury z pliku
     * @param e przechwyt przycisku
     */
    public void load(ActionEvent e) {
        fileChooser = new JFileChooser();
        fileChooser.setSize(400,300);

        Shape shape = new Circle();

        returnVal = fileChooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();

            try{
                br = new BufferedReader(new FileReader(file));


                int i = 0;
                int r = 0, g = 0, b;
                while((currentLine = br.readLine()) != null){
                    switch (i) {
                        case 0:
                            if (currentLine.charAt(0) == 'c') {
                                shape = new Circle();
                                Surface.shapes.add(shape);
                            }
                            if (currentLine.charAt(0) == 'r') {
                                shape = new Rectangle();
                                Surface.shapes.add(shape);
                            }
                            if (currentLine.charAt(0) == 't') {
                                shape = new Triangle();
                                Surface.shapes.add(shape);
                            }
                            break;
                        case 1:
                            try {
                                int x1 = Integer.parseInt(currentLine);
                                shape.setX1(x1);
                            } catch (NumberFormatException ex) {}
                            break;
                        case 2:
                            try {
                                int y1 = Integer.parseInt(currentLine);
                                shape.setY1(y1);
                            } catch (NumberFormatException ex) {}
                            break;
                        case 3:
                            try {
                                int x2 = Integer.parseInt(currentLine);
                                shape.setX2(x2);
                            } catch (NumberFormatException ex) {}
                            break;
                        case 4:
                            try {
                                int y2 = Integer.parseInt(currentLine);
                                shape.setY2(y2);
                            } catch (NumberFormatException ex) {}
                            break;
                        case 5:
                            try {
                                r = Integer.parseInt(currentLine);
                            } catch (NumberFormatException ex) {}
                            break;
                        case 6:
                            try {
                                g = Integer.parseInt(currentLine);
                            } catch (NumberFormatException ex) {}
                            break;
                        case 7:
                            try {
                                b = Integer.parseInt(currentLine);
                                shape.setColor(new Color(r, g, b));
                            } catch (NumberFormatException ex) {}
                            break;
                    }
                    i++;
                    if (i == 8) i = 0;
                }
                surface.repaint();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}

/** Klasa przestrzeni do rysowania
 * @see Shape
 */
class Surface extends JPanel {

    /** Deklaracja: zmiennej odpowiadajacej za wybor, listy figur, dodawanej figury,
     * popup menu, przycisku do zmiany koloru, okna dialogowego ze zmiana koloru,
     * przycisku do potwierdzenia dodania koloru, miejsc na tekst w oknie z ustawieniem kolorow
     * oraz ustawianego koloru
     */
    public int choice;
    static ArrayList<Shape> shapes = new ArrayList<>();
    Shape currentShape;
    Shape shape;
    private JPopupMenu popupMenu;
    private JDialog colorChooser;
    private JButton ok = new JButton("Ok");
    private JTextArea color1, color2, color3;
    private Color color;
    Button seeColor;

    /** Konstruktor klasy przestrzeni
     */
    public Surface() {
        setDoubleBuffered(true);

        MovingAdapter mouse = new MovingAdapter();
        ScaleHandler wheel = new ScaleHandler();

        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addMouseWheelListener(wheel);

        popup();
    }

    /** Funkcja dodajaca popup menu
     */
    private void popup() {
        popupMenu = new JPopupMenu();
        JMenuItem colorItem = new JMenuItem("Zmien kolor");
        colorItem.addActionListener(this::colorChooser);
        popupMenu.add(colorItem);
        addMouseListener(new MousePopupListener());
    }

    /** Metoda obslugujaca przycisk w popup menu
     * @param e przechwyt myszki
     */

    private void colorChooser(ActionEvent e) {
        Color nowy = JColorChooser.showDialog(this, "zmiana koloru", null);
        for (int i = shapes.size()-1; i >= 0; i--) {
            if(shapes.get(i).contains(x,y)){
                shapes.get(i).setColor(nowy);
                repaint();
                break;
            }
        }
    }

    /** Klasa myszy uruchamiajaca popup menu
     */
    class MousePopupListener extends MouseAdapter {

        /** Metoda dla ktorej wlacza sie popup menu
         * @param e przechwyt myszki
         */
        public void mousePressed(MouseEvent e) { checkPopup(e); }

        /** Metoda dla ktorej wlacza sie popup menu
         * @param e przechwyt myszki
         */
        public void mouseClicked(MouseEvent e) { checkPopup(e); }

        /** Metoda dla ktorej wlacza sie popup menu
         * @param e przechwyt myszki
         */
        public void mouseReleased(MouseEvent e) { checkPopup(e); }

        /** Metoda uruchamiajaca popup menu
         * @param e przechwyt myszki
         */
        private void checkPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    /** Metoda rysujaca figury
     * @param g grafika
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        for (Shape p:shapes) p.Draw(g);
    }

    int x, y;

    /** Klasa przyciskow myszy do rysowania figur i przesuwania ich
     * @see Shape
     */
    class MovingAdapter extends MouseAdapter {

        /** Metoda ustalajaca poczatek rysowania i dodaje nowa figure do listy
         * @param e przechwyt myszki
         */
        public void mousePressed(MouseEvent e) {

            switch (choice) {
                case 1:
                    shape = new Circle();
                    break;
                case 2:
                    shape = new Rectangle();
                    break;
                case 3:
                    shape = new Triangle();
                    break;
            }

            if (choice == 1 || choice == 2 || choice == 3){
                shapes.add(shape);

                shape.setX1(e.getX());
                shape.setY1(e.getY());
            }

            if (choice == 0) {
                x = e.getX();
                y = e.getY();

                for(int i = shapes.size()-1; i >= 0; i--){
                    if(shapes.get(i).contains(x,y)) {
                        currentShape = shapes.get(i);
                        break;
                    }

                }
            }
        }

        /** Metoda ustawiajaca wybor na 0, zeby po wybraniu figury mozna bylo pozniej ja edytowac
         * @param e przechwyt myszki
         */
        public void mouseReleased(MouseEvent e) {
            choice = 0;
            currentShape = null;
        }

        /** Metoda ustalajaca wielkosc figury oraz zmieniajaca jej polozenie
         * @param e przechwyt myszki
         */
        public void mouseDragged(MouseEvent e) {

            if(choice == 1 || choice == 2 || choice == 3){
                shape.setX2(e.getX());
                shape.setY2(e.getY());

                repaint();
            }

            int dx;
            int dy;

            if (choice == 0) {

                dx = e.getX() - x;
                dy = e.getY() - y;

                try{ currentShape.move(dx, dy);
                } catch(NullPointerException ignored) {}
                repaint();

                x += dx;
                y += dy;
            }
        }
    }

    /** Klasa kola myszy do zmiany wielkosci figury
     * @see Shape
     */
    class ScaleHandler implements MouseWheelListener {

        /** Metoda zmieniajaca wielkosc figury
         * @param e przechwyt myszki
         */
        public void mouseWheelMoved(MouseWheelEvent e) {

            x = e.getX();
            y = e.getY();

            if (choice == 0) {
                for (int i = shapes.size()-1; i >= 0; i--){
                    if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL){
                        if (shapes.get(i).contains(x, y)){

                            int amount = e.getWheelRotation() * 3;

                            shapes.get(i).resize(amount);
                            repaint();
                            break;
                        }
                    }
                }
            }
        }
    }
}

/** Interfejs dla figury
 */
interface Shape {

    /** Metoda zwracajaca nazwe figury
     * @return nazwa nazwa figury
     */
    String nazwa();

    /** Metoda ustawiajaca pierwsza wspolrzedna poczatkowego punktu
     * @param x1 pierwsza wspolrzedna poczatkowego punktu
     */
    void setX1(int x1);
    /** Metoda ustawiajaca druga wspolrzedna poczatkowego punktu
     * @param y1 druga wspolrzedna poczatkowego punktu
     */
    void setY1(int y1);
    /** Metoda ustawiajaca pierwsza wspolrzedna koncowego punktu
     * @param x2 pierwsza wspolrzedna koncowego punktu
     */
    void setX2(int x2);
    /** Metoda ustawiajaca druga wspolrzedna koncowego punktu
     * @param y2 druga wspolrzedna koncowego punktu
     */
    void setY2(int y2);

    /** Metoda ustawiajaca pierwsza wspolredna poczatkowego punktu
     * @return x1 pierwsza wspolrzedna poczatkowego punktu
     */
    int getX1();
    /** Metoda ustawiajaca druga wspolredna poczatkowego punktu
     * @return y1 druga wspolrzedna poczatkowego punktu
     */
    int getY1();
    /** Metoda ustawiajaca pierwsza wspolredna koncowego punktu
     * @return x2 pierwsza wspolrzedna koncowego punktu
     */
    int getX2();
    /** Metoda ustawiajaca druga wspolredna koncowego punktu
     * @return y2 druga wspolrzedna koncowego punktu
     */
    int getY2();

    /** Metoda rysujaca fugure
     * @param g grafika
     */
    void Draw(Graphics g);

    /** Metoda sprawdzajaca czy nakierowano na figure
     * @param x polozenie myszki w poziomie
     * @param y polozenie myszki w pionie
     * @return zwraca czy nakierowano na figure
     */
    boolean contains(int x, int y);

    /** Metoda przesuwajaca figure
     * @param x wartosc o ile przesunieto myszka w poziomie
     * @param y wartosc o ile przesunieto myszka w pionie
     */
    void move(int x, int y);
    /** Metoda zmieniajaca wielkosc figury
     * @param amount wartosc o ile przekrecono scrolla
     */
    void resize(int amount);

    /** Metoda zmieniajaca kolor figury
     * @param color kolor
     */
    void setColor(Color color);

    /** Metoda zmieniajaca kolor figury
     * @return color kolor
     */
    Color getColor();

}

/** Klasa reprezentujaca kolo
 */
class Circle implements Shape {

    /** Deklaracja punktow lewego gornego i prawego dolnego rogu kola,
     * oraz zmiennych do ktorych przypisywane sa maksymalne i minimalne wartosci tych punktow,
     * promienia i koloru
     */
    int x1, y1, x2, y2, a, b, c, d, e;
    Color color = new Color(0);

    /** Metoda zwracajaca nazwe figury
     * @return nazwa kolo
     */
    public String nazwa() { return "circle"; }

    /** Metoda ustawiajaca pierwsza wspolrzedna jednego punktu
     * @param x1 pierwsza wspolrzedna poczatkowego punktu
     */
    public void setX1(int x1) { this.x1 = x1; }

    /** Metoda ustawiajaca druga wspolrzedna jednego punktu
     * @param y1 druga wspolrzedna poczatkowego punktu
     */
    public void setY1(int y1) { this.y1 = y1; }

    /** Metoda ustawiajaca pierwsza wspolrzedna drugiego punktu
     * @param x2 pierwsza wspolrzedna koncowego punktu
     */
    public void setX2(int x2) { this.x2 = x2; }

    /** Metoda ustawiajaca druga wspolrzedna drugiego punktu
     * @param y2 druga wspolrzedna koncowego punktu
     */
    public void setY2(int y2) { this.y2 = y2; }

    /** Metoda ustawiajaca pierwsza wspolredna poczatkowego punktu
     * @return x1 pierwsza wspolrzedna poczatkowego punktu
     */
    public int getX1() { return this.x1; }
    /** Metoda ustawiajaca druga wspolredna poczatkowego punktu
     * @return y1 druga wspolrzedna poczatkowego punktu
     */
    public int getY1() { return this.y1; }
    /** Metoda ustawiajaca pierwsza wspolredna koncowego punktu
     * @return x2 pierwsza wspolrzedna koncowego punktu
     */
    public int getX2() { return this.x2; }
    /** Metoda ustawiajaca druga wspolredna koncowego punktu
     * @return y2 druga wspolrzedna koncowego punktu
     */
    public int getY2() { return this.y2; }

    /** Metoda wyznaczajaca maksymalne i minimalne wartosci punktow oraz rysujaca kolo
     * @param g grafika
     */
    public void Draw(Graphics g){
        a = max(x1, x2);
        b = min(x1, x2);
        c = max(y1, y2);
        d = min(y1, y2);
        e = max(a - b, c - d);

        g.setColor(color);
        g.fillOval(b, d, e, e);

    }

    /** Metoda sprawdzajaca czy najechano mysza na figure
     * @param x polozenie myszki w poziomie
     * @param y polozenie myszki w pionie
     * @return zwraca czy nakierowano na figure
     */
    public boolean contains(int x, int y) { return (x > b && x < (e + b) && y > d && y < (e + d)); }

    /** Metoda przesuwajaca figure
     * @param x wartosc o ile przesunieto myszka w poziomie
     * @param y wartosc o ile przesunieto myszka w pionie
     */
    public void move(int x, int y) {
        this.x1 += x;
        this.y1 += y;
        this.x2 += x;
        this.y2 += y;
    }

    /** Metoda zmieniajaca wielkosc figury
     * @param amount wartosc o ile przekrecono scrolla
     */
    public void resize(int amount){
        if (x1 > x2){ this.x1 += amount; }
        if (x1 < x2){ this.x2 += amount; }
        if (y1 > y2){ this.y1 += amount; }
        if (y1 < y2){ this.y2 += amount; }
    }

    /** Metoda ustawiajaca kolor dla figury
     * @param color kolor
     */
    public void setColor(Color color) { this.color = color; }

    /** Metoda ustawiajaca kolor dla figury
     * @return color kolor
     */
    public Color getColor() { return this.color; }
}

/** Klasa reprezentujaca prostokat
 */
class Rectangle implements Shape {

    /** Deklaracja punktow lewego gornego i prawego dolnego rogu prostokata,
     * oraz zmiennych do ktorych przypisywane sa maksymalne i minimalne wartosci tych punktow
     * oraz koloru
     */
    int x1, y1, x2, y2, a, b, c, d;
    Color color = new Color(0);

    /** Metoda zwracajaca nazwe figury
     * @return nazwa prostokat
     */
    public String nazwa() { return "rectangle"; }

    /** Metoda ustawiajaca pierwsza wspolrzedna jednego punktu
     * @param x1 pierwsza wspolrzedna poczatkowego punktu
     */
    public void setX1(int x1) { this.x1 = x1; }

    /** Metoda ustawiajaca druga wspolrzedna jednego punktu
     * @param y1 druga wspolrzedna poczatkowego punktu
     */
    public void setY1(int y1) { this.y1 = y1; }

    /** Metoda ustawiajaca pierwsza wspolrzedna drugiego punktu
     * @param x2 pierwsza wspolrzedna koncowego punktu
     */
    public void setX2(int x2) { this.x2 = x2; }

    /** Metoda ustawiajaca druga wspolrzedna drugiego punktu
     * @param y2 druga wspolrzedna koncowego punktu
     */
    public void setY2(int y2) { this.y2 = y2; }

    /** Metoda ustawiajaca pierwsza wspolredna poczatkowego punktu
     * @return x1 pierwsza wspolrzedna poczatkowego punktu
     */
    public int getX1() { return this.x1; }
    /** Metoda ustawiajaca druga wspolredna poczatkowego punktu
     * @return y1 druga wspolrzedna poczatkowego punktu
     */
    public int getY1() { return this.y1; }
    /** Metoda ustawiajaca pierwsza wspolredna koncowego punktu
     * @return x2 pierwsza wspolrzedna koncowego punktu
     */
    public int getX2() { return this.x2; }
    /** Metoda ustawiajaca druga wspolredna koncowego punktu
     * @return y2 druga wspolrzedna koncowego punktu
     */
    public int getY2() { return this.y2; }

    /** Metoda wyznaczajaca maksymalne i minimalne wartosci punktow oraz rysujaca prostokat
     * @param g grafika
     */
    public void Draw(Graphics g){
        a = max(x1, x2);
        b = min(x1, x2);
        c = max(y1, y2);
        d = min(y1, y2);

        g.setColor(color);
        g.fillRect(b, d, a - b, c - d);
    }

    /** Metoda sprawdzajaca czy najechano mysza na figure
     * @param x polozenie myszki w poziomie
     * @param y polozenie myszki w pionie
     * @return zwraca czy nakierowano na figure
     */
    public boolean contains(int x, int y) { return (x > b && x < a && y > d && y < c); }

    /** Metoda przesuwajaca figure
     * @param x wartosc o ile przesunieto myszka w poziomie
     * @param y wartosc o ile przesunieto myszka w pionie
     */
    public void move(int x, int y) {
        this.x1 += x;
        this.y1 += y;
        this.x2 += x;
        this.y2 += y;
    }

    /** Metoda zmieniajaca wielkosc figury
     * @param amount wartosc o ile przekrecono scrolla
     */
    public void resize(int amount){
        if (x1 > x2){ this.x1 += amount; }
        if (x1 < x2){ this.x2 += amount; }
        if (y1 > y2){ this.y1 += amount; }
        if (y1 < y2){ this.y2 += amount; }
    }

    /** Metoda ustawiajaca kolor dla figury
     * @param color kolor
     */
    public void setColor(Color color) { this.color = color; }

    /** Metoda ustawiajaca kolor dla figury
     * @return color kolor
     */
    public Color getColor() { return this.color; }
}

/** Klasa reprezentujaca trojkat
 */
class Triangle implements Shape {

    /** Deklaracja punktow lewego gornego i prawego dolnego rogu prostokata,
     * oraz zmiennych do ktorych przypisywane sa maksymalne i minimalne wartosci tych punktow
     * oraz koloru
     */
    int x1, y1, x2, y2, a, b, c, d;
    Color color = new Color(0);

    /** Metoda zwracajaca nazwe figury
     * @return nazwa trojkat
     */
    public String nazwa() { return "triangle"; }

    /** Metoda ustawiajaca pierwsza wspolrzedna jednego punktu
     * @param x1 pierwsza wspolrzedna poczatkowego punktu
     */
    public void setX1(int x1) { this.x1 = x1; }

    /** Metoda ustawiajaca druga wspolrzedna jednego punktu
     * @param y1 druga wspolrzedna poczatkowego punktu
     */
    public void setY1(int y1) { this.y1 = y1; }

    /** Metoda ustawiajaca pierwsza wspolrzedna drugiego punktu
     * @param x2 pierwsza wspolrzedna koncowego punktu
     */
    public void setX2(int x2) { this.x2 = x2; }

    /** Metoda ustawiajaca druga wspolrzedna drugiego punktu
     * @param y2 druga wspolrzedna koncowego punktu
     */
    public void setY2(int y2) { this.y2 = y2; }

    /** Metoda ustawiajaca pierwsza wspolredna poczatkowego punktu
     * @return x1 pierwsza wspolrzedna poczatkowego punktu
     */
    public int getX1() { return this.x1; }
    /** Metoda ustawiajaca druga wspolredna poczatkowego punktu
     * @return y1 druga wspolrzedna poczatkowego punktu
     */
    public int getY1() { return this.y1; }
    /** Metoda ustawiajaca pierwsza wspolredna koncowego punktu
     * @return x2 pierwsza wspolrzedna koncowego punktu
     */
    public int getX2() { return this.x2; }
    /** Metoda ustawiajaca druga wspolredna koncowego punktu
     * @return y2 druga wspolrzedna koncowego punktu
     */
    public int getY2() { return this.y2; }

    /** Metoda wyznaczajaca maksymalne i minimalne wartosci punktow,
     * tworzaca tablice wspolrzednych wierzcholkow trojkata oraz rysujaca trojkat
     * @param g grafika
     */
    public void Draw(Graphics g){
        a = max(x1, x2);
        b = min(x1, x2);
        c = max(y1, y2);
        d = min(y1, y2);

        int[] xx = {a, b, (a + b)/2};
        int[] yy = {c, c, d};

        g.setColor(color);
        g.fillPolygon(xx, yy, 3);
    }

    /** Metoda sprawdzajaca czy najechano mysza na figure
     * @param x polozenie myszki w poziomie
     * @param y polozenie myszki w pionie
     * @return zwraca czy nakierowano na figure
     */
    public boolean contains(int x, int y) { return (x > b && x < a && y > d && y < c); }

    /** Metoda przesuwajaca figure
     * @param x wartosc o ile przesunieto myszka w poziomie
     * @param y wartosc o ile przesunieto myszka w pionie
     */
    public void move(int x, int y) {
        this.x1 += x;
        this.y1 += y;
        this.x2 += x;
        this.y2 += y;
    }

    /** Metoda zmieniajaca wielkosc figury
     * @param amount wartosc o ile przekrecono scrolla
     */
    public void resize(int amount){
        if (x1 > x2){ this.x1 += amount; }
        if (x1 < x2){ this.x2 += amount; }
        if (y1 > y2){ this.y1 += amount; }
        if (y1 < y2){ this.y2 += amount; }
    }

    /** Metoda ustawiajaca kolor dla figury
     * @param color kolor
     */
    public void setColor(Color color) { this.color = color; }

    /** Metoda ustawiajaca kolor dla figury
     * @return color kolor
     */
    public Color getColor() { return this.color; }
}
