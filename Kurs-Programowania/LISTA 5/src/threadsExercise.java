/** Glowna klasa
 * @author Bartosz Grelewski
 */
public class threadsExercise {

    /**
     * glowna metoda testujaca
     * @param args argumenty: szerokosc, wysokosc, szybkosc zmiany, prawdopodobienstwo
     */
    public static void main(String[] args) {

        try {
            if (args.length != 4) {
                System.out.println("Podano zla ilosc argumentow");
                return;
            }
            int n = Integer.parseInt(args[0]);
            int m = Integer.parseInt(args[1]);
            int k = Integer.parseInt(args[2]);
            double p = Double.parseDouble(args[3]);
            Gui gui = new Gui(n,m,k,p);
        } catch(NumberFormatException e) {
            System.out.println("Podano zle argumenty");
        }


    }
}