#include <iostream>
#include <random>
#include <algorithm>
#include <functional>

void descGenerator(int arr[], int n){
    std::random_device rd;

    arr[n];

    std::mt19937 mt(rd());
    std::uniform_int_distribution<int> dist(1.0, 2*n-1);

    for(int i = 0; i < n; i++ ) {
        arr[i] = ((int)dist(mt));
    }

    std::sort(arr, arr + n, std::greater<int>());
}