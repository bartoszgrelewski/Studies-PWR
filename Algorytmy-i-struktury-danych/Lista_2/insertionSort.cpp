#include <iostream>

/* Function to sort an array using insertion sort*/
void insertionSort(int *arr, int n, int &compares_n, int &swaps_n) {
    compares_n = 0;
    swaps_n = 0;
    int i, key, j;
    for (i = 1; i < n; i++) {
        key = arr[i];
        j = i - 1;

        /* Move elements of arr[0..i-1], that are
        greater than key, to one position ahead
        of their current position */
        while (j >= 0 && arr[j] > key) {
            compares_n++;

            //swap
            arr[j + 1] = arr[j];
            swaps_n++;

            j = j - 1;
        }
        //swap
        arr[j + 1] = key;
        swaps_n++;
    }
}