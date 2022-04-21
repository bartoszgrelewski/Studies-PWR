import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class MyFrame extends JFrame {

    JButton button;

    MyFrame(){
        this.setTitle("Trojkat Pascala - Bartosz Grelewski");
        this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setBounds(500,250,100,100);
        ImageIcon grelewski = new ImageIcon("znaczek_czarny.png");
        this.setIconImage(grelewski.getImage()); //zmienia ikone okna

        JLabel label = new JLabel();

//tworzenie paneli
        JPanel uppanel = new JPanel();
        JPanel downpanel = new JPanel(new GridLayout(0,3));

        JLabel tresc = new JLabel("Podaj ilosc rzedow: ",SwingConstants.CENTER);
        tresc.setFont(new Font("Courier", Font.PLAIN, 15));
        JTextPane trojkat = new JTextPane();
        trojkat.setFont(new Font("Courier", Font.PLAIN, 15));


        JTextField wpisywanie = new JTextField();//wpisywanie
        wpisywanie.setFont(new Font("Courier", Font.PLAIN, 15));
        wpisywanie.setHorizontalAlignment(JTextField.CENTER);
        trojkat.setText(" ");

        trojkat.setEditable(false);
        uppanel.add(trojkat);
        uppanel.setBackground(Color.white);

        button = new JButton();
        button.addActionListener(e -> fill(wpisywanie.getText(), trojkat) ); //AKCJA

        button.setText("Wyswietl");
        button.setFont(new Font("Courier", Font.PLAIN, 15));
        button.setFocusable(false);
        button.setForeground(Color.BLACK);
        button.setBackground(Color.GRAY);

        downpanel.add(tresc);
        downpanel.add(wpisywanie);
        downpanel.add(button);
        uppanel.add(label);


        this.add(uppanel);
        this.add(downpanel);
        this.pack(); //skaluje do wielkosci obiektow
        this.setVisible(true);
    }
    public void fill(String dana, JTextPane wiersz) {

        pascalTrianglePrint obiekt1 = new pascalTrianglePrint();
        try {
            int n = Integer.parseInt(dana);
            if(n >= 0) {
                wiersz.setText(" ");
                for (int i = 0; i <= n; i++) {
                    wiersz.setText(wiersz.getText() + "\n" + obiekt1.stworzWiersz(i, n));
                }
                pack();
                StyledDocument doc = wiersz.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
            }
            else{
                wiersz.setText("Podano liczbe ujemna");
            }
            pack();
        } catch (Exception e) {
            wiersz.setText("Wprowadzono bledna dana");
            pack();
        }
    }
}
