import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Frame extends JFrame {

    JButton button;
    JLabel tresc;
    JTextPane label;
    JPanel upPanel;
    JPanel downPanel;
    JTextField wpisywanie;

    Frame () {
        this.setTitle("Proste GUI - Bartosz Grelewski");
        this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setBounds(500,250,0,0);

        //tworzenie paneli
        upPanel = new JPanel();
        downPanel = new JPanel(new GridLayout(0,3));

        upPanel.setBackground(Color.white);

        label = new JTextPane();
        tresc = new JLabel("Podaj dane: ",SwingConstants.CENTER);    //podaj dane
        tresc.setFont(new Font("Courier", Font.PLAIN, 15));
        wpisywanie = new JTextField();                                      //wpisywanie
        label.setEditable(false);

        button = new JButton();
        button.addActionListener(e -> printElement(wpisywanie.getText())); //AKCJA
        wpisywanie.setHorizontalAlignment(JTextField.CENTER);
        button.setPreferredSize(new Dimension(25, 25));
        button.setText("Wyswietl");
        button.setFont(new Font("Courier", Font.PLAIN, 15));
        button.setFocusable(false);

        downPanel.add(tresc);
        downPanel.add(wpisywanie);
        downPanel.add(button);
        upPanel.add(label);

        this.add(upPanel);
        this.add(downPanel);
        this.pack();
        this.setVisible(true);
    }

    public void printElement(String text) {

        try {
            Runtime runTime = Runtime.getRuntime();
            String executablePath = "src/./LISTA1";
            Process process = runTime.exec(executablePath + " " + text);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String temp1;
            String temp2 = "";
            temp1 = reader.readLine();
            pack();

            while (temp1 != null) {
                temp2 = temp2 + temp1 + "\n";
                temp1 = reader.readLine();
                pack();
            }
            label.setText(temp2);
            this.pack();
            StyledDocument doc = label.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        wpisywanie.setText("");
        pack();
    }
}
