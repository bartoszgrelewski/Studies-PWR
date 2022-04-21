public class main {
    public static void main(String[] args) {
        int n, k; // n=pierwszy argument (wiersz), k=kolumna
        try {
            n = Integer.parseInt(args[0]);
            wierszTrojkataPascala wierszTrojkataPascala = new wierszTrojkataPascala(n);

            for(int i = 1; i < args.length; i++) {
                try {
                    k = Integer.parseInt(args[i]);
                    System.out.println(args[i] + " - " + wierszTrojkataPascala.wspolczynnik(k));
                } catch (NumberFormatException e) {
                    System.out.println("podano niepoprawna kolumne");
                } catch (mojException e) {
                    System.out.println("Podano liczbe spoza zakresu");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("podano niepoprawny wiersz");
        } catch (mojException e) {
            System.out.println("nieprawidlowy numer wiersza");
        }
    }
}

class wierszTrojkataPascala {
    int[] wiersz;
    wierszTrojkataPascala(int n) throws mojException {
        if(n<=0)
            throw new mojException();
        wiersz = new int[n + 1];
        int a = 1, b, c;// n!, k!, (n - k)!
        wiersz[0] = 1;
        for(int i = 1; i <= n; i++) {
            a *= i;
        }
        for(int i = 1; i <= n; i++) {
            b = 1;
            c = 1;
            for(int j = 1; j <= i; j++) {
                b *= j;
            }
            for(int j = 1; j <= n - i; j++) {
                c *= j;
            }
            wiersz[i] = a/(b*c);
        }
    }

    int wspolczynnik(int k) throws mojException {
        if(k<0 || k>wiersz.length) //wyrzuca
            throw new mojException();
        return wiersz[k];
    }
}
class mojException extends Exception {}