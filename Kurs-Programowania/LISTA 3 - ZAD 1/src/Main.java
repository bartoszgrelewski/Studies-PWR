import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        if(args.length == 0) {
            System.exit(0);
        }

        int licznik = 1;
        double zmienna;
        policz[] Tablica = new policz[args[0].length()]; //tablica interfejsow

        for (int i = 0; i < args[0].length(); i++){

            char symbolFigura = args[0].charAt(i); // charAt(i) przechodzi po tablicy char 0-wego argumentu
//KOLO
            if(symbolFigura == 'o'){

                try{
                    zmienna = Double.parseDouble(args[licznik]); //KONWERTOWANIE
                    if(zmienna>0) {
                        Kolo obiektKolo = (Kolo) Figura.Operator(Figura.jedenARG.KOLO, zmienna);
                        Tablica[i] = obiektKolo;
                        licznik++;
                    } else {
                        System.out.println((args[licznik]) + " - " + "Nie mozna utworzyc kola, bo promien jest mniejszy od 1");
                        Tablica[i]= null;
                        licznik++;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println(args[licznik] + " - " + "Podany promien, nie jest liczba");
                    licznik++;
                }
//CZOWOROKAT
            } else if(symbolFigura == 'c') {
                double[] boki = new double[4]; //czworokat ma 4 boki
                double kat=0;
                int j=0;
                try {
                    for (j = 0; j < 4; j++) {
                        boki[j] = Double.parseDouble(args[licznik + j]); //z kazdym bokiem argument dodaje sie
                    }
                    Arrays.sort(boki); //sortowanie rosnaco tablicy
                    kat = Double.parseDouble(args[licznik + 4]);
                    if (kat > 0 && kat < 180){

                        if ((boki[0] == boki[1]) && (boki[1] == boki[2]) && (boki[2] == boki[3])) {
                            if (kat == 90) {
                                Kwadrat obiektKwadrat = (Kwadrat) Figura.Operator(Figura.jedenARG.KWADRAT, boki[0]);
                                Tablica[i] = obiektKwadrat;
                            } else {
                                Romb obiektRomb = (Romb) Figura.Operator(Figura.dwaARG.ROMB, boki[0],kat);
                                Tablica[i] = obiektRomb;
                            }
                        } else {
                            Prostokat obiektProstokat = (Prostokat) Figura.Operator(Figura.dwaARG.PROSTOKAT, boki[0], boki[2]);
                            Tablica[i] = obiektProstokat;
                        }
                }
                    else {
                        System.out.println("Podano nieprawidlowy kat");
                    }

                } catch(NumberFormatException ex) {
                    System.out.println(args[licznik+j] + " - " + "Bledny bok, program nie moze policzyc pola i obwodu ");
                }
                licznik+=5; //tu juz jest na kolejnej zmiennej
//PIECIOKAT
            } else if(symbolFigura == 'p'){

                try{
                    zmienna = Double.parseDouble(args[licznik]); //KONWERTOWANIE
                    if(zmienna>0){
                        Pieciokat obiektPieciokat = (Pieciokat) Figura.Operator(Figura.jedenARG.PIECIOKAT, zmienna);
                        Tablica[i] = obiektPieciokat;
                        licznik++;
                    } else {
                        System.out.println((args[licznik]) + " - " + "Nie mozna utworzyc pieciokatu, bo bok jest mniejszy od 1");
                        Tablica[i]= null;
                        licznik++;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println(args[licznik] + " - " + "Podany bok, nie jest liczba");
                    licznik++;
                }
//SZESCIOKAT
            } else if(symbolFigura == 's'){

                try{
                    zmienna = Double.parseDouble(args[licznik]); //KONWERTOWANIE
                    if(zmienna>0){
                        Szesciokat obiektSzesciokat = (Szesciokat) Figura.Operator(Figura.jedenARG.SZESCIOKAT, zmienna);
                        Tablica[i] = obiektSzesciokat;
                    } else {
                        System.out.println((args[licznik]) + " - " + "Nie mozna utworzyc szesciokatu, bo bok jest mniejszy od 1");
                        Tablica[i]= null;
                    }
                    licznik++;
                } catch (NumberFormatException ex) {
                    System.out.println(args[licznik] + " - " + "Podany bok, nie jest liczba");
                    licznik++;
                }
            } else {
                System.out.println(args[0].charAt(i) + " - " + "To niepoprawna dana");
                licznik++;
            }
        }
        for (int k=0; k<Tablica.length; k++){
            if(Tablica[k]!= null) {
                System.out.println(Tablica[k].getClass().getName() + " pole: " + Tablica[k].pole());
                System.out.println(Tablica[k].getClass().getName() + " obwod: " + Tablica[k].obwod());
            }
        }
    }
}