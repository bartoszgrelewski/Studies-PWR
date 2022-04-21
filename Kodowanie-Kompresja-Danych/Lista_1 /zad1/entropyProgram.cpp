#include <iostream>
#include <fstream>
#include <math.h>
#include <map>


class EntropyCalculator {
private:
    std::ifstream file;
    int size = 0;
    std::map<char, int> randomVariables;
    std::map<std::string, int> conditionalRandomVariables;
    std::string doubleChar = "aa";


    double Read(std::string argument) {
        file.exceptions ( std::ifstream::badbit );
        try {
            file.open(argument);
        }
        catch (const std::ifstream::failure& e) {
            std::cout << "Exception opening/reading file";
        }
        char currentChar = 0;
        char previousChar = 0;

        while (file >> std::noskipws >> currentChar) {
             randomVariables[currentChar]++;

             doubleChar[0] = previousChar;
             doubleChar[1] = currentChar;

             conditionalRandomVariables[doubleChar]++;
             previousChar = currentChar;
             size++;
        }
        file.close();
    }

public:
    EntropyCalculator(std::string argument) {
        Read(argument);
    }

    double calculateEntropy(std::map<char, int>&map, int size) {
        std::map<char, int>::iterator it;
        double answer = 0;

        for (it = map.begin(); it != map.end(); it++)
            answer += it->second * log2(it->second);

        return - (answer / size - log2(size));
    }

    double calculateConditionalEntropy(std::map<char, int>&singleChars, std::map<std::string, int>&doubleChars, int size) {
        std::map<std::string, int>::iterator it;
        double answer = 0;

        for (it = doubleChars.begin(); it != doubleChars.end(); it++)
            if (singleChars[it->first[0]] != 0)
                answer += it->second *( log2(it->second) - log2(singleChars[it->first[0]]));

        return - (answer / size);
    }

    void ShowResults() {
        double e = calculateEntropy(randomVariables, size);
        double ce = calculateConditionalEntropy(randomVariables, conditionalRandomVariables, size);


        std::cout << "  Entropy:               " << e << std::endl;
        std::cout << "  Conditional entropy:   " << ce << std::endl;
        std::cout << "  Difference:            " << e - ce << std::endl;
    }

};

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("You have to type in an argument which is correctly written name of file.");
        return 0;
    }

    std::string argument = argv[1];
    EntropyCalculator *ec = new EntropyCalculator(argument);
    ec->ShowResults();
    return 0;
}