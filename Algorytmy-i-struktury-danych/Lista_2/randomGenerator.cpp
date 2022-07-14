#include <random>

void randomGenerator(int arr[], int n) {
    std::random_device rd;
    arr[n];
    std::mt19937 mt(rd());
    std::uniform_int_distribution<int> dist(1, 2*n-1);

    for (int i=0; i<n; ++i) {
        arr[i] = (int)dist(mt);
    }
}