import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Klasa odpowiedzialna za GUI
 * dostaje in oraz out
 */
public class Gui extends JFrame {

    /**
     * deklaracja zmiennych, ktore sluza
     * za wysylanie oraz odbieranie informacji
     * z serwera i do serwera
     */
    private final BufferedReader in;
    private final PrintWriter out;

    /**
     * konstruktor GUI (okna)
     * @param in - zmienna, ktora klient
     * @param out - zmienna, ktora dostaje serwer
     */
    public Gui(BufferedReader in, PrintWriter out) {
        super("Binary Tree");
        this.in = in;
        this.out = out;
        printtree = new JLabel("",SwingConstants.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu();
        setSize(300, 100);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * deklaracja okien dialogowych, miejsca na tekst,
     * tekstu dla bledu, zmiennej ktora wypisuje drzewo
     */
    JDialog setType, action;
    JTextArea data;
    JLabel error;
    JLabel printtree;

    /**
     * metoda bedaca MENU, w ktorym uzytkownik wybiera sposrod opcji
     * insert, search, delete, write oraz exit
     * oprocz MENU jest tu tez okienko, w ktorym wybieramy typ drzewa sposrod
     * INTEGER, DOUBLE, STRING
     */
    private void menu() {
       JMenuBar mainMenu = new JMenuBar();

       JMenuItem searchItem = new JMenuItem("search");
       searchItem.setMaximumSize(new Dimension(60, 40));
       mainMenu.add(searchItem);
       searchItem.addActionListener(this::search);

       JMenuItem insertItem = new JMenuItem("insert");
       insertItem.setMaximumSize(new Dimension(60, 40));
       mainMenu.add(insertItem);
       insertItem.addActionListener(this::insert);

       JMenuItem deleteItem = new JMenuItem("delete");
       deleteItem.setMaximumSize(new Dimension(60, 40));
       mainMenu.add(deleteItem);
       deleteItem.addActionListener(this::delete);

       JMenuItem writeItem = new JMenuItem("write");
       writeItem.setMaximumSize(new Dimension(60, 40));
       mainMenu.add(writeItem);
       writeItem.addActionListener(this::write);

       JMenuItem exitItem = new JMenuItem("exit");
       exitItem.setMaximumSize(new Dimension(60, 40));
       mainMenu.add(exitItem);
       exitItem.addActionListener(this::exit);

       setJMenuBar(mainMenu);

       setType = new JDialog(this, "choose type", false);
       setType.setSize(320, 70);
       setType.setLocationRelativeTo(null);
       setType.setDefaultCloseOperation(0);
       setType.setLayout(new FlowLayout());

       JButton Integer = new JButton("integer");
       setType.add(Integer);
       Integer.addActionListener(this::setInteger);

       JButton Double = new JButton("double");
       setType.add(Double);
       Double.addActionListener(this::setDouble);


       add(printtree);

       JButton String = new JButton("string");
       setType.add(String);
       String.addActionListener(this::setString);
       setType.setVisible(true);

       printtree.setFont(new Font("Lato",Font.PLAIN,25));
       pack();
    }

    /**
     * metoda tworzaca drzewo dla opcji INTEGER
     * @param e - przechwyt akcji
     */
    public void setInteger(ActionEvent e) {
        out.println("integer");
        setType.setVisible(false);
    }

    /**
     * metoda tworzaca drzewo dla opcji DOUBLE
     * @param e - przechwyt akcji
     */
    public void setDouble(ActionEvent e) {
        out.println("double");
        setType.setVisible(false);
    }

    /**
     * metoda tworzaca drzewo dla opcji STRING
     * @param e - przechwyt akcji
     */
    public void setString(ActionEvent e) {
        out.println("string");
        setType.setVisible(false);
    }

    /**
     * metoda tworzaca okno w ktorym wpisuje sie wartosc do szukania
     * @param e przechwyt
     */
    public void search(ActionEvent e) {
        action = new JDialog(this, "search", false);
        action.setLocationRelativeTo(null);
        action.setSize(200, 80);
        action.setResizable(false);
        action.setLayout(new FlowLayout());

        data = new JTextArea();
        data.setColumns(5);

        JButton ok = new JButton("ok");
        action.add(ok);
        ok.addActionListener(this::searchOk);
        action.add(data);

        action.setVisible(true);
    }

    /**
     * metoda zamykajaca okno wyszukiwania elementu w drzewie
     * @param e przechwyt
     */
    public void searchOk(ActionEvent e) {
        out.println("search " + data.getText());
        System.out.println(getRespond());
        action.setVisible(false);
    }

    /**
     * metoda sprawdzajaca komunikacje z serwerem
     * sprawdza czy klient wpisal wlasciwy tekst
     */
    private void checkRespond() {
        try {
            String line = in.readLine();

            if (!line.equals("success")) {
                data.setText("");
                error.setText(line);
            } else {
                action.setVisible(false);
            }
        } catch (IOException ex) {
            System.out.println("Read failed");
            System.exit(-1);
        }
    }

    /**
     * metoda sprawdzajaca czy dane przekazane serwerowi sa wlasciwe
     */
    private String getRespond() {
        try {
            return in.readLine();
        } catch (IOException ex) {
            System.out.println("Read failed");
            System.exit(-1);
            return null;
        }
    }

    /**
     * metoda tworzaca okno w ktorym wpisuje sie wartosc do dodawania
     * @param e przechwyt
     */
    public void insert(ActionEvent e) {
        action = new JDialog(this, "insert", false);
        action.setLocationRelativeTo(null);
        action.setSize(200, 80);
        action.setResizable(false);
        action.setLayout(new FlowLayout());

        data = new JTextArea();
        data.setColumns(5);

        error = new JLabel("");

        JButton ok = new JButton("ok");
        action.add(ok);
        ok.addActionListener(this::insertOk);
        action.add(data);
        action.add(error);

        action.setVisible(true);
    }

    /**
     * metoda przekazujca serwerowi dane z wpisywania
     * kolejnych elementow do drzewa
     * @param e - przechwyt
     */
    public void insertOk(ActionEvent e) {
        out.println("insert " + data.getText());
        checkRespond();
    }

    /**
     * metoda tworzaca okno w ktorym wpisuje sie wartosc do usuniecia
     * @param e przechwyt
     */
    public void delete(ActionEvent e) {
        action = new JDialog(this, "delete", false);
        action.setLocationRelativeTo(null);
        action.setSize(200, 80);
        action.setResizable(false);
        action.setLayout(new FlowLayout());

        data = new JTextArea();
        data.setColumns(5);

        error = new JLabel("");

        JButton ok = new JButton("ok");
        action.add(ok);
        ok.addActionListener(this::deleteOk);
        action.add(data);
        action.add(error);

        action.setVisible(true);
    }

    /**
     * metoda zamykajaca okno i usuwajaca wartosc z drzewa
     * @param e przechwyt
     */
    public void deleteOk(ActionEvent e) {
        out.println("delete " + data.getText());
        checkRespond();
    }

    /**
     * metoda wypsujaca drzewo
     * @param e przechwyt
     */
    public void write(ActionEvent e) {
        out.println("write ");
        printtree.setText(getRespond());
        pack();
    }

    /**
     * metoda zamykajaca program
     * @param e przechwyt
     */
    public void exit(ActionEvent e) { System.exit(0); }
}
