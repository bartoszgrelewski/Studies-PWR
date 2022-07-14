// A utility function to swap two elements
int compares_n_temp;
int swaps_n_temp;

void swap(int* a, int* b)
{
    int t = *a;
    *a = *b;
    *b = t;
    swaps_n_temp++;
}
/* This function takes last element as pivot, places
the pivot element at its correct position in sorted
array, and places all smaller (smaller than pivot)
to left of pivot and all greater elements to right
of pivot */

int partition_quick (int arr[], int low, int high)
{
    int pivot = arr[high]; // pivot
    int i = (low - 1); // Index of smaller element and indicates the right position of pivot found so far

    for (int j = low; j <= high - 1; j++)
    {
        // If current element is smaller than the pivot
        if (arr[j] < pivot)
        {
            compares_n_temp++;
            i++; // increment index of smaller element
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);
    return (i + 1);
}

void quickSort(int arr[], int low, int high) {
    if (low < high) {
        /* pi is partitioning index, arr[p] is now
        at right place */
        int pi = partition_quick(arr, low, high);

        // Separately sort elements before
        // partition and after partition
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}
void quickSortRec(int arr[], int low, int high, int &compares_n, int &swaps_n) {
    quickSort(arr, low, high);
    compares_n = compares_n_temp;
    swaps_n = swaps_n_temp;
}