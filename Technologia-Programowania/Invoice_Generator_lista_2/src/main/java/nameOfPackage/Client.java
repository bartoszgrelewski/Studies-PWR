package nameOfPackage;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa odpowiedzialna za fakture EXPERT
 * client jest Creator odnośnie Product.
 */
public class Client {
  String imie;
  String nazwisko;
  String miasto;
  String kodPocztowy;
  String ulica;
  List<Product> productList = new ArrayList<>();

  /**
   * Konstruktor klasy Client.
   *
   * @param imie         - imie klienta
   * @param nazwisko     - nazwisko klienta
   * @param miasto       - miasto klienta
   * @param kodPocztowy - kod pocztowy klienta
   * @param ulica        - ulica klienta
   */
  public Client(String imie, String nazwisko, String miasto, String kodPocztowy, String ulica) {
    this.imie = imie;
    this.nazwisko = nazwisko;
    this.miasto = miasto;
    this.kodPocztowy = kodPocztowy;
    this.ulica = ulica;
  }

  /**
   * Metoda tworzaca i dodajaca produkt/usluge do listy obiektow.
   *
   * @param name     - nazwa produktu/uslugi
   * @param price    - cena jednostkowa produktu/uslugi
   * @param quantity - ilosc produktu/uslug
   */
  public void addProduct(String name, float price, int quantity) {
    Product product = new Product(name, price, quantity);
    productList.add(product);
  }

  /**
   * Metoda wypisujaca czesc faktury zwiazana z produktami/uslugamim.
   */
  public void showFactur() {
    float suma = 0;
    for (Product prod : productList) {
      System.out.println(productList.indexOf(prod) + 1 + ". Nazwa: " + prod.getName() + " Ilosc: "
          + prod.getQuantity() + " Cena jednostkowa: " + prod.getPrice() + " Cena calkowita: "
          + prod.getQuantity() * prod.getPrice());
      suma = suma + sumPrice(prod.getPrice(), prod.getQuantity());
    }
    System.out.println("\n" + "Suma calkowita " + suma);
  }

  /**
   * Metoda obliczajaca sume glowna dla metody showFactur.
   *
   * @param price    - wartosc za usluge/produkt
   * @param quantity - ilosc uslug/produktow
   * @return suma - zwraca gotowa sume
   */
  public float sumPrice(float price, int quantity) {
    float suma = 0;
    suma = suma + price * quantity;
    return suma;
  }

  /**
   * Getter dla imienia klienta.
   *
   * @return imie
   */
  public String getImie() {
    return imie;
  }

  /**
   * Getter dla nazwiska klienta.
   *
   * @return nazwisko
   */
  public String getNazwisko() {
    return nazwisko;
  }

  /**
   * Getter dla miasta.
   *
   * @return - miasto
   */
  public String getMiasto() {
    return miasto;
  }

  /**
   * Getter dla kodu pocztowego.
   *
   * @return - kod pocztowy
   */
  public String getKodPocztowy() {
    return kodPocztowy;
  }

  /**
   * Getter dla ulicy.
   *
   * @return - ulica
   */
  public String getUlica() {
    return ulica;
  }
}
