abstract public class Figura implements policz {
    public enum jedenARG {
        KOLO, KWADRAT, PIECIOKAT, SZESCIOKAT;
    }
    public enum dwaARG {
        PROSTOKAT, ROMB;
    }
    public static double ObliczPole(jedenARG typ, double zmienna) {
        Figura figura = Operator (typ, zmienna);
        double wynik = figura.pole();
        return wynik;
    }
    public static double ObliczObwod(jedenARG typ, double zmienna) {
        return  Operator (typ, zmienna).obwod(); //to samo co u góry
    }

    public static double ObliczPole(dwaARG typ, double zmienna, double drugazmienna) {
        Figura figura = Operator (typ, zmienna, drugazmienna);
        double wynik = figura.pole();
        return wynik;
    }
    public static double ObliczObwod(dwaARG typ, double zmienna, double drugazmienna) {
        return  Operator (typ, zmienna, drugazmienna).obwod(); //to samo co u góry
    }

    public static Figura Operator(jedenARG typ, double bok) {
        switch (typ) {
            case KOLO -> {
                Kolo kolo = new Kolo(bok);
                return kolo;
            }
            case KWADRAT -> {
                Kwadrat kwadrat = new Kwadrat(bok);
                return kwadrat;
            }
            case PIECIOKAT -> {
                Pieciokat pieciokat = new Pieciokat(bok);
                return pieciokat;
            }
            case SZESCIOKAT -> {
                Szesciokat szesciokat = new Szesciokat(bok);
                return szesciokat;
            }
            default -> {
                return null;
            }
        }
    }

    public static Figura Operator(dwaARG typ, double bok, double bok2) {
        switch (typ) {
            case PROSTOKAT -> {
                Prostokat prostokat = new Prostokat(bok, bok2);
                        return prostokat;
            }
            case ROMB -> {
                Romb romb = new Romb(bok, bok2);
                return romb;
            }
            default -> {
                return null;
            }
        }

    }
}