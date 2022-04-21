package nameOfPackage;
/**
 * Klasa odpowiedzialna za przechowywanie informacji o produkcie EXPERT.
 */
public class Product {
  String nazwa;
  int ilosc;
  float cena;

  /**
   * Konstruktor dla produktu.
   *
   * @param nazwa - nazwa produktu/uslugi
   * @param cena  - cena produktu/ uslugi
   * @param ilosc - ilosc produktu/uslugi
   */
  public Product(String nazwa, float cena, int ilosc) {
    this.nazwa = nazwa;
    this.ilosc = ilosc;
    this.cena = cena;
  }

  /**
   * Getter zwracajacy cena*ilosc.
   *
   * @return - iloczyn
   */
  public float getMult() {
    return getPrice() * getQuantity();
  }

  /**
   * Getter zwracajacy nazwe produktu.
   *
   * @return - nazwa
   */
  public String getName() {
    return nazwa;
  }

  /**
   * Getter zwracajacy cene produktu.
   *
   * @return - cena
   */
  public float getPrice() {
    return cena;
  }

  /**
   * Getter zwracajacy ilosc.
   *
   * @return - ilosc
   */
  public int getQuantity() {
    return ilosc;
  }
}
