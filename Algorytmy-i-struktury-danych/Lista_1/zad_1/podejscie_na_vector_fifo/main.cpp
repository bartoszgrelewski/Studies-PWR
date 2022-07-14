//podejscie na vectorach
#include <iostream>
#include <vector>

int main() {
    std::vector<int> fifo;
    std::vector<int> lifo;

    for (int i = 100; i >= 1; i--)
        fifo.push_back(i);
    for (int i = 1; i <= 100; i++)
        lifo.push_back(i);

    //dodawanie do FIFO
    //fifo.push_back(1000);
    //fifo.push_back(101);

    //usuwanie z FIFO
    //fifo.erase(fifo.begin());

    //dodawanie do LIFO
    //lifo.push_back(1000);
    //lifo.push_back(101);

    //usuwanie z FIFO
    //fifo.erase(fifo.end());

//sprawdzenie vectorow
std::cout <<"fifo: "<< fifo.size() << std::endl;
std::cout <<"lifo: "<< lifo.size() << std::endl;

    for (std::vector<int>::iterator it = fifo.begin() ; it != fifo.end(); ++it)
        std::cout << ' ' << *it;
    std::cout << '\n';

    for (std::vector<int>::iterator it = lifo.begin() ; it != lifo.end(); ++it)
        std::cout << ' ' << *it;
    std::cout << '\n';

    return 0;
}
