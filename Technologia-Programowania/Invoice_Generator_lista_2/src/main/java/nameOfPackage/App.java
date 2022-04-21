package nameOfPackage;
import java.util.Scanner;

/**
 * Klasa odpowiedzialna za wybor dzialan EXPERT
 * (Niskie sprzezenie)
 * (Wysoka spojnosc)
 * (Indirection - uzywam Getterow do pozyskiwania pol z klas.
 */
public class App {
  static ClientList clients = new ClientList();

  /**
   * glowna metoda.
   *
   * @param args - argumenty
   */
  public static void main(final String[] args) {
    loop(); //extracted
  }

  /**
   * Metoda MENU.
   */
  public static void loop() {
    System.out.println("Program do wystawiania faktur by Bartosz Grelewski");
    while (true) {
      System.out.println("=======================");
      System.out.println("Wybierz dzialanie");
      System.out.println("1) Utworz fakture");
      System.out.println("2) Wyswietl klientow");
      System.out.println("3) Zamknij program");
      System.out.println("=======================");

      final Scanner scanner = new Scanner(System.in);
      final int scannerInput = scanner.nextInt();

      switch (scannerInput) {
        case 1 -> clients.addClientData();
        case 2 -> clients.seeClient();
        case 3 -> System.exit(1);
        default -> System.out.println("Nieprawidlowy znak, wpisz ponownie");
      }
    }
  }
}