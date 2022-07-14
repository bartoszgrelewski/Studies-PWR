#include <iostream>
#include <random>
#include <algorithm>

void ascGenerator(int arr[], int n) {
    std::random_device dev;
    arr[n];
    std::mt19937 rng(dev());
    std::uniform_int_distribution<int> dist(1, 2*n-1);

    for(int i = 0; i < n; i++ ) {
        arr[i] = ((int)dist(dev));
    }

    std::sort(arr, arr + n);
}