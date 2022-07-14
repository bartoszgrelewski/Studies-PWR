#include<iostream>
using namespace std;

class stos{
public:
    stos(){i=0;f=1;el = NULL;}
    ~stos() //zwolnienie pamięci dla elementów stosu
    {
        while(el!=NULL)
        {
            pom = el->poprzedni;
            delete el;
            el = pom;
        }
    }
    unsigned int size(){return i;}//liczba elementów w kolejce
    void push(int x)//dodanie elementu na szczyt stosu
    {
        if(f)//sprawdzamy, czy pierwszy raz dodajemy element
        {
            f=0;
            el = new wezel;
            el->element = x;
            el->poprzedni = NULL;
        }
        else
        {
            pom = new wezel;
            pom->element = x;
            pom->poprzedni = el;
            el = pom;
        }
        ++i;
    }
    int pop()// usunięcie elementu z wierzchołka
    {
        if(el == NULL) return 0; //gdy stos jest pusty

        pom = el->poprzedni;
        delete el;
        el = pom;
        --i;

        return 1;
    }
    int value() //zwrócenie ostatniego elementu na stosie
    {
        if(!empty())
            return el->element;

        return ERROR;
    }
    bool empty()
    {
        return i==0?1:0;
    }
    const int ERROR = 1000000000; //ta wartosć nie może się pojawić na stosie
private:
    bool f; //flaga okreslająca, czy w kolejce cos już się pojawiło
    struct wezel{
        int element; //wartosć elementu stosu
        wezel *poprzedni;  //wskaźnik na poprzedni element
    };
    unsigned int i; //zmienna przechowuje liczbę elementów na stosie
    wezel *el, //ostatni element na stosie
    *pom; //zmienna pomocnicza
};

int main()
{
    stos stack; //utworzenie stosu

    stack.push(12); //dodanie na stos 12
    stack.push(23); //dodanie na stos 23
    stack.push(43); //dodanie na stos 43
    stack.push(99); //dodanie na stos 99


    stack.pop(); //usunięcie 99
    stack.pop(); //usunięcie 43

    stack.pop(); //usunięcie 23
    stack.pop(); //usunięcie 12
    if(stack.empty())
        cout<<"Stos jest pusty\n";
    else
        cout<<"Na stosie znajdują się elementy, jest ich "<<stack.size()<<endl;

    if(!stack.pop())
        cout<<"Nie można usunąć elementu ze stosu, ponieważ jest on pusty\n";

    return 0;
}