import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Gui extends JFrame {
   public Gui() {
        super("Binary Tree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu();
        setSize(300, 50);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    JDialog setType, action;
    JTextArea data;
    int type;

    Tree<Integer> integerTree;
    Tree<Double> doubleTree;
    Tree<String> stringTree;


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

       JButton String = new JButton("string");
       setType.add(String);
       String.addActionListener(this::setString);

       setType.setVisible(true);
   }
   public void setInteger(ActionEvent e) {
        integerTree = new Tree<>();
        type = 1;
        setType.setVisible(false);
    }

   public void setDouble(ActionEvent e) {
       doubleTree = new Tree<>();
       type = 2;
       setType.setVisible(false);
   }

    public void setString(ActionEvent e) {
        stringTree = new Tree<>();
        type = 3;
        setType.setVisible(false);
    }

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


    public void searchOk(ActionEvent e) {

        switch (type) {
            case 1:
                try {
                    int d = Integer.parseInt(data.getText());
                    System.out.println(integerTree.search(d));
                } catch(NumberFormatException ex) {
                    System.out.println("bledna dana");
                }
                break;
            case 2:
                try {
                    double d = Double.parseDouble(data.getText());
                    System.out.println(doubleTree.search(d));
                } catch(NumberFormatException ex) {
                    System.out.println("bledna dana");
                }
                break;
            case 3:
                System.out.println(stringTree.search(data.getText()));
                break;
        }
        action.setVisible(false);
    }


    public void insert(ActionEvent e) {
        action = new JDialog(this, "insert", false);
        action.setLocationRelativeTo(null);
        action.setSize(200, 80);
        action.setResizable(false);
        action.setLayout(new FlowLayout());

        data = new JTextArea();
        data.setColumns(5);

        JButton ok = new JButton("ok");
        action.add(ok);
        ok.addActionListener(this::insertOk);
        action.add(data);

        action.setVisible(true);
    }

    public void insertOk(ActionEvent e) {

        switch (type) {
            case 1:
                try {
                    int d = Integer.parseInt(data.getText());
                    integerTree.insert(d);
                } catch(NumberFormatException ex) {
                    System.out.println("bledna dana");
                }
                break;
            case 2:
                try {
                    double d = Double.parseDouble(data.getText());
                    doubleTree.insert(d);
                } catch(NumberFormatException ex) {
                    System.out.println("bledna dana");
                }
                break;
            case 3:
                stringTree.insert(data.getText());
                break;
        }
        action.setVisible(false);
    }

    public void delete(ActionEvent e) {
        action = new JDialog(this, "delete", false);
        action.setLocationRelativeTo(null);
        action.setSize(200, 80);
        action.setResizable(false);
        action.setLayout(new FlowLayout());

        data = new JTextArea();
        data.setColumns(5);

        JButton ok = new JButton("ok");
        action.add(ok);
        ok.addActionListener(this::deleteOk);
        action.add(data);

        action.setVisible(true);
    }

    public void deleteOk(ActionEvent e) {

        switch (type) {
            case 1:
                try {
                    int d = Integer.parseInt(data.getText());
                    integerTree.delete(d);
                } catch(NumberFormatException ex) {
                    System.out.println("bledna dana");
                }
                break;
            case 2:
                try {
                    double d = Double.parseDouble(data.getText());
                    doubleTree.delete(d);
                } catch(NumberFormatException ex) {
                    System.out.println("bledna dana");
                }
                break;
            case 3:
                stringTree.delete(data.getText());
                break;
        }
        action.setVisible(false);
    }

    public void write(ActionEvent e) {
        switch (type) {
            case 1:
                System.out.println(integerTree.inOrder());
                break;
            case 2:
                System.out.println(doubleTree.inOrder());
                break;
            case 3:
                System.out.println(stringTree.inOrder());
                break;
        }
    }

    public void exit(ActionEvent e) { System.exit(0); }
}
