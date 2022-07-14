#include <iostream>

int swaps;
int compares;

void ascGenerator(int arr[], int n) {
    for(int i = 0; i < n; i++ ) {
        arr[i] = i;
    }
}

void printArray(int A[], int size) {
    for (auto i = 0; i < size; i++)
        std::cout << A[i] << " ";
}

// A recursive binary search function. It returns
// location of x in given array arr[l..r] is present,
// otherwise -1
int binarySearch(int arr[], int l, int r, int x)
{
    if (r >= l) {
        compares++;
        int mid = l + (r - l) / 2;

        // If the element is present at the middle
        // itself
        if (arr[mid] == x) {
            compares++;
            return mid;
        }
        // If element is smaller than mid, then
        // it can only be present in left subarray
        if (arr[mid] > x) {
            compares++;
            return binarySearch(arr, l, mid - 1, x);
        }
        // Else the element can only be present
        // in right subarray
        return binarySearch(arr, mid + 1, r, x);
    }

    // We reach here when element is not
    // present in array
    return 0;
}

int main() {

    int *arr, n, i;

    std::cout<<"Enter size of array : ";
    std::cin >> n;
    arr = new int[n];
    ascGenerator(arr,n);
    std::cout<<"Enter element: ";
    int x;
    std::cin>>x;

    int result = binarySearch(arr, 0, n - 1, x);
    if (result == 0) {
    std::cout << "Element is not present in array";
    }
    std::cout << "Element is present at index " << result;
    return 0;
}

//Binary search takes an input of size n,
//spends a constant amount of non-recursive overhead comparing the middle
//element to the searched for element, breaks the original input into half,
//and recursive on only one half of the array. Now plug this into the master
//theorem with a=1, subproblems of size n/b where b=2, and d=0
//Only way to calculate master theorem is to plug in case 2.
//d = log_b(a).
//Thus, the total running time is O(n^0*log_n) = O(log_n).
//T(n) = T(n/2) + O(1)
//O(1) = T(n) - T(n/2) = 2.