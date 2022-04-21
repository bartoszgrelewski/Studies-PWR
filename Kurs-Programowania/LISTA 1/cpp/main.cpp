#include <iostream>
#include <string>
#include <exception>
using namespace std ;

class wierszTrojkataPascala {
    int* wiersz; //tablica
    int rozmiar;
public:
   explicit wierszTrojkataPascala(int n) {
            if(n<=0)
                throw (string)("Niepoprawny wiersz");
            wiersz = new int[n + 1]; //zawsze o jeden więcej od 0 do n
            rozmiar = n+1;
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

    int wspolczynnik(int m) {
            if(m<0 || m>rozmiar) //wyrzuca
                throw (string)("Niepoprawna kolumna");
            return wiersz[m];
    }
    ~wierszTrojkataPascala(){
        delete[] wiersz;
    }
};

int main(int argc,char* argv[]){
    int n, k; // n=pierwszy argument (wiersz), k=kolumna
    try {
        n = stoi(argv[1]);
        wierszTrojkataPascala wierszTrojkataPascala(n);

        for(int i = 2; i < argc; i++) {
            try {
                k = stoi(argv[i]);
                cout<<argv[i] << " - " << wierszTrojkataPascala.wspolczynnik(k)<<endl;
            } catch (const invalid_argument a)
            { cout << argv[i] <<  " - nieprawidlowa dana" << endl;
            } catch (string e) {
                cout<< e <<endl;
            }
        }
    }
    catch (const invalid_argument a) {
        cout << argv[1] <<  " - nieprawidlowa dana" << endl;
    }
    catch (string e) {
        cout<< e <<endl;
    }
}