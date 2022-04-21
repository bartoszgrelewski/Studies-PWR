import java.io.*;
import java.net.*;

/**
 * klasa serwera
 */
public class SocketServer {
  ServerSocket server = null;
  Socket client = null;
  BufferedReader in = null;
  PrintWriter out = null;
  String line = "";

  Tree<Integer> integerTree;
  Tree<Double> doubleTree;
  Tree<String> stringTree;
  int type;

  /**
   * konstruktor serwera
   */
  SocketServer() { 
    try {
      server = new ServerSocket(4444); 
    } 
    catch (IOException e) {
      System.out.println("Could not listen on port 4444"); System.exit(-1);
    }
  }

  /**
   * metoda obslugujaca wysyl i odbieranie danych
   */
  public void listenSocket() {
    try {
      client = server.accept();
    } catch (IOException e) {
      System.out.println("Accept failed: 4444"); System.exit(-1);
    }
    try {
      // Odbieranie od socketa
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      // Wysylanie do socketa
      out = new PrintWriter(client.getOutputStream(), true);
    } catch (IOException e) {
      System.out.println("Accept failed: 4444"); System.exit(-1);
    }
      try {
        line = in.readLine();

        switch (line) {
          case "integer" -> setInteger();
          case "double" -> setDouble();
          case "string" -> setString();
        }
      } catch (IOException e) {
        System.out.println("Read failed"); System.exit(-1);
      }
     // break;
    //}
    while (line != null) {
      try {
          // Odbieranie od socketa
        line = in.readLine();
        // Wypisywanie na serwerze
        System.out.println(line);
        // Wysylanie do socketa

        String cur = line.split(" ")[0];
        switch (cur) {
          case "insert" -> insertOk(line.substring(cur.length() + 1));
          case "delete" -> deleteOk(line.substring(cur.length() + 1));
          case "search" -> searchOk(line.substring(cur.length() + 1));
          case "write" -> write();
        }
      } catch (IOException e) {
        System.out.println("Read failed"); System.exit(-1);
      } 
    }
  }

  /**
   * metoda zamykajaca
   */
  protected void finalize() {
    try {
      in.close();
      out.close();
      client.close();
      server.close();
    } catch (IOException e) {
      System.out.println("Could not close."); System.exit(-1);
    }
  }

  /**
   * metoda tworzaca drzewo typu INTEGER
   */
  public void setInteger() {
    integerTree = new Tree<>();
    type = 1;
  }

  /**
   * metoda tworzaca drzewo typu DOUBLE
   */
  public void setDouble() {
    doubleTree = new Tree<>();
    type = 2;
  }

  /**
   * metoda tworzaca drzewo typu STRING
   */
  public void setString() {
    stringTree = new Tree<>();
    type = 3;
  }

  /**
   * metoda przekazujaca klientowi informacje true/false
   * odnosnie istnienia danego elementu w drzewie
   * @param s - tekst
   */
  public void searchOk(String s) {

    switch (type) {
      case 1:
        try {
          int d = Integer.parseInt(s);
          out.println(integerTree.search(d));
        } catch(NumberFormatException ex) {
          out.println("invalid data");
        }
        break;
      case 2:
        try {
          double d = Double.parseDouble(s);
          out.println(doubleTree.search(d));
        } catch(NumberFormatException ex) {
          out.println("invalid data");
        }
        break;
      case 3:
        out.println(stringTree.search(s));
        break;
    }
  }

  /**
   * metoda przekazujaca klientowi informacje o tym, ze
   * dana zostala dobrze dodana do drzewa
   * @param s - tekst
   */
  public void insertOk(String s) {

    switch (type) {
      case 1:
        try {
          int d = Integer.parseInt(s);
          integerTree.insert(d);
          out.println("success");
        } catch(NumberFormatException ex) {
          out.println("invalid data");
        }
        break;
      case 2:
        try {
          double d = Double.parseDouble(s);
          doubleTree.insert(d);
          out.println("success");
        } catch(NumberFormatException ex) {
          out.println("invalid data");
        }
        break;
      case 3:
        stringTree.insert(s);
        out.println("success");
        break;
    }
  }

  /**
   * metoda przekazujaca klientowi, ze zostal
   * dobrze usuniety element
   * @param s - tekst
   */
  public void deleteOk(String s) {

    switch (type) {
      case 1:
        try {
          int d = Integer.parseInt(s);
          integerTree.delete(d);
          out.println("success");
        } catch(NumberFormatException ex) {
          out.println("invalid data");
        }
        break;
      case 2:
        try {
          double d = Double.parseDouble(s);
          doubleTree.delete(d);
          out.println("success");
        } catch(NumberFormatException ex) {
          out.println("invalid data");
        }
        break;
      case 3:
        stringTree.delete(s);
        out.println("success");
        break;
    }
  }

  /**
   * metoda dzialajaca w zaleznosci od wartosci
   * zmiennej type, ktora przekazuje klientowi gotowe
   * drzewo w jendym z trzech typow
   */
  public void write() {
    switch (type) {
      case 1 -> out.println(integerTree.inOrder());
      case 2 -> out.println(doubleTree.inOrder());
      case 3 -> out.println(stringTree.inOrder());
    }
  }

  /**
   * glowna metoda testujaca serwer
   * @param args - argumenty
   */
  public static void main(String[] args) {
    SocketServer server = new SocketServer();
    server.listenSocket();
  }
}