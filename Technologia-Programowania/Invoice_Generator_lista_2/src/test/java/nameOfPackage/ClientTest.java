package nameOfPackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientTest {

  @Test
  void sumPrice() {
    final Product testowyProdukt1 = new Product("TestowyProdukt1", 10,5);
    final Product testowyProdukt2 = new Product("TestowyProdukt2", 1,5);

    assertEquals(55, testowyProdukt1.getMult() +
        testowyProdukt2.getMult() , "Laczna suma za produkty");

    assertEquals(50,testowyProdukt1.getMult(), "Jeden produkt");
  }
}
