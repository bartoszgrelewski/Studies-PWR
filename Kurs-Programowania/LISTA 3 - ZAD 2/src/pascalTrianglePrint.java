public class pascalTrianglePrint {

    //konstruktor
    public pascalTrianglePrint() {
    }

    //liczenie n po k wedle wzoru n!/(k!*(n-k)!) k = rzad
    public long policzLiczbe (int n, int rzad) {
        return silnia(n)/(silnia(rzad) * silnia(n-rzad));
    }

    //liczenie silni iteracyjnie
    public long silnia (int n) {
        long iloczyn = 1;
        for (int i = 1; i <= n; i++) {
            iloczyn *= i;
        }
        return iloczyn;
    }
/////// NAJWAZNIEJSZA
    public String stworzWiersz(int rzad, int iloscRzedow) {
        int maksymalnaDlugosc = Double.valueOf(Math.pow(2, iloscRzedow)).toString().length();

        String liczby = "";
        for(int i = 0; i <= maksymalnaDlugosc - iloscRzedow; i++)
            liczby += " ";

        for(int j = 0; j <= rzad; j++){
            long liczba = policzLiczbe(rzad, j);
            if(j == rzad) {
                liczby = liczby + liczba;
            }
            else {
                liczby = liczby + liczba + spacje(liczba, maksymalnaDlugosc);
            }
        }
        return liczby;
    }
///////
    private String spacje(final Long liczba, final int maksymalnaDlugosc) {
        String spacje = "";
        for (int i = 0; i < maksymalnaDlugosc - liczba.toString().length(); i++)
        {
            spacje = spacje + " ";
        }
        return spacje;
    }

}

