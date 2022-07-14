#include <random>
#include <iostream>

int swaps = 0, compares = 0;

void randomGenerator(int arr[], int n) {
    std::random_device rd;
    arr[n];
    std::mt19937 mt(rd());
    std::uniform_int_distribution<int> dist(1, 2*n-1);

    for (int i=0; i<n; ++i) {
        arr[i] = (int)dist(mt);
    }
}

void swap(int* a, int* b) {
    int t = *a;
    *a = *b;
    *b = t;
    swaps++;
}

void printArray(int A[], int size) {
    for (auto i = 0; i < size; i++)
        std::cout << A[i] << " ";
}

int partition_r (int *arr, int low, int high) {
    int pivot = arr[high]; // pivot
    int i = (low - 1); // Index of smaller element and indicates the right position of pivot found so far

    for (int j = low; j <= high - 1; j++)
    {
        // If current element is smaller than the pivot
        if (arr[j] < pivot)
        {
            compares++;
            i++; // increment index of smaller element
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);
    return (i + 1);
}

/*
 * Returns the ith order statistic i.e., the ith smallest number in arr[p...r]
 * 0 <= p <= r < arr.length and 1 <= i <= arr.length
 */
int recursiveSelect(int *arr, int p, int r, int i)	{

    if(p == r) {
        compares++;
        return arr[p];
    }
    int q = partition_r(arr, p, r);
    int k = q - p + 1;
    if(i == k) {
        compares++;
        return arr[q];
    }
    else if(i < k) {
        compares++;
        return recursiveSelect(arr, p, q - 1, i);
    }
    else return recursiveSelect(arr, q + 1, r, i - k);
}
/*
 * Returns the ith order statistic i.e., the ith smallest number in A[0...n-1]
 * n == A.length and 1 <= i <= n
 */


int main() {
    int *arr, N, i;

    std::cout<<"Enter size of array : ";
    std::cin >> N;
    arr = new int[N];

    randomGenerator(arr,N);
    printArray(arr, N);

    std::cout<<"\nEnter i (0 to exit): ";
    std::cin >> i;
    for (auto i = 1; i <= N; i++) {
        std::cout<<i<<"th order statistic :\n";
        std::cout<<recursiveSelect(arr, 0, N-1, i)<<"\n";
        std::cout<<"END for i: "<< i << "\n";
    }
    if(N <= 50){
        std::cout <<"Sorted array: \n";
        printArray(arr, N);
        std::cout<<"\n";
    }
    std::cout<<"Randomized Select Swaps : "<<swaps<<"\n";
    std::cout<<"Randomized Select Compares : "<<compares<<"\n";
    return 0;
}