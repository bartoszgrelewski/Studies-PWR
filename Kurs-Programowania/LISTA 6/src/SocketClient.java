import java.io.*;
import java.net.*;

/**
 * klasa klienta
 */
public class SocketClient {

  Socket socket = null;
  PrintWriter out = null;
  BufferedReader in = null;

  /**
   * metoda odpowiedzialna za komunikacje z serwerem
   */
  public void listenSocket(){
    try {
      socket = new Socket("localhost", 4444);
      // Polaczenie z socketem
      // Wysylanie do serwera
      out = new PrintWriter(socket.getOutputStream(), true);
      // Odbieranie z serwera
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    catch (UnknownHostException e) {
       System.out.println("Unknown host: localhost"); System.exit(1);
     }
     catch  (IOException e) {
       System.out.println("No I/O"); System.exit(1);
     }
  }

  /** Glowna funkcja testujaca - ze strony klienta
   * @param args argumenty
   */
  public static void main(String[] args){
    SocketClient client = new SocketClient();
    client.listenSocket();
    Gui gui = new Gui(client.in, client.out);
    gui.setVisible(true);
  }
}
