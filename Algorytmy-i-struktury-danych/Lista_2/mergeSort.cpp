#include <iostream>


int compares_nn;
int swaps_nn;

void printArray(int A[], int size);

void merge(int array[], int const left, int const mid, int const right)
{
    auto const subArrayOne = mid - left + 1;
    auto const subArrayTwo = right - mid;

    // Create temp arrays
    auto *leftArray = new int[subArrayOne],
        *rightArray = new int[subArrayTwo];

    // Copy data to temp arrays leftArray[] and rightArray[]
    for (auto i = 0; i < subArrayOne; i++)
        leftArray[i] = array[left + i];
    for (auto j = 0; j < subArrayTwo; j++)
        rightArray[j] = array[mid + 1 + j];

    auto indexOfSubArrayOne = 0, // Initial index of first sub-array
    indexOfSubArrayTwo = 0; // Initial index of second sub-array
    int indexOfMergedArray = left; // Initial index of merged array

    // Merge the temp arrays back into array[left..right]
    while (indexOfSubArrayOne < subArrayOne && indexOfSubArrayTwo < subArrayTwo) {
        compares_nn += 2;
        if (leftArray[indexOfSubArrayOne] <= rightArray[indexOfSubArrayTwo]) {
            compares_nn++;
            array[indexOfMergedArray] = leftArray[indexOfSubArrayOne];
            indexOfSubArrayOne++;
            swaps_nn++;
        }
        else {
            array[indexOfMergedArray] = rightArray[indexOfSubArrayTwo];
            swaps_nn++;
            indexOfSubArrayTwo++;
        }
        indexOfMergedArray++;
    }
    // Copy the remaining elements of
    // left[], if there are any
    while (indexOfSubArrayOne < subArrayOne) {
        compares_nn++;
        array[indexOfMergedArray] = leftArray[indexOfSubArrayOne];
        swaps_nn++;
        indexOfSubArrayOne++;
        indexOfMergedArray++;
    }
    // Copy the remaining elements of
    // right[], if there are any
    while (indexOfSubArrayTwo < subArrayTwo) {
        compares_nn++;
        array[indexOfMergedArray] = rightArray[indexOfSubArrayTwo];
        swaps_nn++;
        indexOfSubArrayTwo++;
        indexOfMergedArray++;
    }
}

// begin is for left index and end is
// right index of the sub-array
// of arr to be sorted */
void mergeSort(int array[], int const begin, int const end) {
    if (begin >= end)
        return; // Returns recursively

    auto mid = begin + (end - begin) / 2;
    mergeSort(array, begin, mid);
    mergeSort(array, mid + 1, end);

    //print array before merge
    std::cout<<"Array before merge \n";
    printArray(array, end);
    merge(array, begin, mid, end);

    //print array after merge
    std::cout<<"Array after merge \n";
    printArray(array, end);
}

void mergeSortRec(int array[], int const begin, int const end, int &compares, int &swaps) {
    mergeSort(array,begin,end);
    compares = compares_nn;
    swaps = swaps_nn;
}