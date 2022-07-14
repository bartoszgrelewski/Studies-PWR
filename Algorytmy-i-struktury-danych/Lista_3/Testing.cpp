#include <random>
#include <fstream>
#include "Select.cpp"
#include "RandomizedSelect.cpp"
#include <math.h>

void SelectTest() {
    int k = 100, c = 8, median = 7;
    float temp_k = 0;
    float avg_swaps = 0;
    float avg_compares = 0;

    std::string filename("Select7_sort.CSV");
    std::ofstream fout;
    fout.open(filename, std::ios_base::app);
    fout << "n;AVG_Compares;AVG_Swaps;\n";
    for (int n = 100; n <= 10000; n += 100) {
            fout << n;

            for(int h = 0; h < k; h++) {
                swaps = 0;
                compares = 0;
                int arr[n];

                randomGenerator(arr, n);
                find_kth(arr, n, c, median);
                avg_compares += compares;
                avg_swaps += swaps;
            }
            temp_k = k;
            fout << ';' << floor(avg_compares / temp_k) << ';' << floor(avg_swaps / temp_k) << ';' << "\n";
            avg_swaps = 0;
            avg_compares = 0;
    }
    fout.close();
}

void RandomSelectTest() {
    int k = 100, c = 8;
    float temp_k = 0;
    float avg_swaps = 0;
    float avg_compares = 0;

    std::string filename("RandomSelect_sort.CSV");
    std::ofstream fout;
    fout.open(filename, std::ios_base::app);
    fout << "n;AVG_Compares;AVG_Swaps;\n";
    for (int n = 100; n <= 10000; n += 100) {
        fout << n;

        for(int h = 0; h < k; h++) {
            swaps = 0;
            compares = 0;
            int arr[n];

            randomGenerator(arr, n);
            recursiveSelect(arr, 0, n-1, c);
            avg_compares += compares;
            avg_swaps += swaps;
        }
        temp_k = k;
        fout << ';' << floor(avg_compares / temp_k) << ';' << floor(avg_swaps / temp_k) << ';' << "\n";
        avg_swaps = 0;
        avg_compares = 0;
    }
    fout.close();
}

int main() {

    SelectTest();
    RandomSelectTest();
    printArray(arr,n);
    cout<<"\n";
    std::cout<<k<<"th order statistic :\n";

    std::cout<<"END for k: "<< k << "\n";

    printArray(arr,n);
    std::cout<<"\n"<<swaps<<"\n"<<compares;

}
