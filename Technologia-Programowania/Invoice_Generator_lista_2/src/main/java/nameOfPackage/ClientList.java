package nameOfPackage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Klasa odpowiedzialna za wypisywanie wprowadzonych danych przez klienta (EXPERT).
 * (Creator odnosnie client).
 */
public class ClientList {
  private final List<Client> clientList = new ArrayList<>();
  Scanner scanner = new Scanner(System.in);
  private String imie;
  private String nazwisko;
  private String miasto;
  private String kodPocztowy;
  private String ulica;

  /**
   * Metoda zczytujaca dane klienta z klawiatura, ktora przekazuje je metodzie addClientToList.
   */
  public void addClientData() {

    System.out.println("Podaj imie: ");
    imie = scanner.nextLine();
    System.out.println("Podaj nazwisko: ");
    nazwisko = scanner.nextLine();
    System.out.println("Podaj miasto: ");
    miasto = scanner.nextLine();
    System.out.println("Podaj kod pocztowy: ");
    kodPocztowy = scanner.nextLine();
    System.out.println("Podaj ulice: ");
    ulica = scanner.nextLine();

    addClientToList();

  }

  /**
   * Metoda tworzaca obiekt klienta i wpisujaca do niego dane z metdoy addClientData.
   */
  public void addClientToList() {
    Client client = new Client(imie, nazwisko, miasto, kodPocztowy, ulica);
    clientList.add(client);

    addProductsToClient(client);
  }

  /**
   * Metoda wypisujaca liste klientow.
   */
  public void seeClient() {
    if (clientList.isEmpty()) {
      System.out.println("Lista klientow jest pusta, wypelnij by zobaczyc.");
      App.loop();
    }
    System.out.println("=======================");
    System.out.println("Wybierz klienta, ktorego fakture chcesz wyswietlic");
    System.out.println("=======================");

    for (Client client : clientList) {
      System.out.println(clientList.indexOf(client) + 1 + ". "
          + client.getImie() + " " + client.getNazwisko());
    }
    chooseClient();
  }

  /**
   * Metoda wypisujaca klienta po wybraniu przez uzytkownika.
   */
  public void chooseClient() {
    Scanner scanner = new Scanner(System.in);
    int scannerInput = scanner.nextInt();
    System.out.print(clientList.get(scannerInput - 1).getImie() + " ");
    System.out.print(clientList.get(scannerInput - 1).getNazwisko() + " ");
    System.out.println(clientList.get(scannerInput - 1).getMiasto() + " ");
    System.out.print(clientList.get(scannerInput - 1).getKodPocztowy() + " ");
    System.out.println(clientList.get(scannerInput - 1).getUlica());
    System.out.println("=======================");

    clientList.get(scannerInput - 1).showFactur();
  }

  /**
   * Metoda dodajaca produkt do klienta.
   *
   * @param client - obiekt
   */
  public void addProductsToClient(Client client) {
    while (true) {
      System.out.println("=======================");
      System.out.println("Podaj nazwe produktu/uslugi");
      final String nameOfProduct = scanner.nextLine();
      System.out.println("Podaj cene produktu");
      float price = 0;
      try {
        price = Float.parseFloat(scanner.nextLine());
      }
      catch (Exception e) {
        System.out.println("Wpisano niepoprawny znak, podaj liczbe");
      }
      System.out.println("Podaj ilosc");

      int quantity = 0;
      try {
        quantity = Integer.parseInt(scanner.nextLine());
      }
      catch (Exception e) {
        System.out.println("Wpisano niepoprawny znak, podaj liczbe");
      }

      client.addProduct(nameOfProduct, price, quantity);
      while (true) {
        System.out.println("Czy dodac kolejny rekord?");
        System.out.println("1. Tak");
        System.out.println("2. Nie");
        String answer = scanner.nextLine();
        if (Objects.equals(answer, "1")) break;
        if (Objects.equals(answer, "2")) return;
        System.out.println("Podaj poprawny numer.");
      }
    }
  }
}
