int partition(int* arr, int low, int high, int* lp);

int d_compares_n;
int d_swaps_n;

void swapPivot(int* a, int* b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
    d_swaps_n++;
}

void DualPivotQuickSort(int* arr, int low, int high) {
    if (low < high) {
        int lp, rp;
        rp = partition(arr, low, high, &lp);
        DualPivotQuickSort(arr, low, lp - 1);
        DualPivotQuickSort(arr, lp + 1, rp - 1);
        DualPivotQuickSort(arr, rp + 1, high);
    }
}

int partition(int* arr, int low, int high, int* lp) {
    d_compares_n++;
    if (arr[low] > arr[high])
        swapPivot(&arr[low], &arr[high]);

    // p is the left pivot, and q is the right pivot.
    int j = low + 1;
    int g = high - 1, k = low + 1, p = arr[low], q = arr[high];
    d_compares_n++;
    while (k <= g) {

        // if elements are less than the left pivot
        d_compares_n += 2;
        if (arr[k] < p) {
            swapPivot(&arr[k], &arr[j]);
            j++;
        }

            // if elements are greater than or equal
            // to the right pivot
        else if (arr[k] >= q) {

            while (arr[g] > q && k < g) {
                d_compares_n += 2;
                g--;
            }
            swapPivot(&arr[k], &arr[g]);
            g--;
            d_compares_n++;
            if (arr[k] < p) {
                swapPivot(&arr[k], &arr[j]);
                j++;
            }
        }
        k++;
    }
    j--;
    g++;

    // bring pivots to their appropriate positions.
    swapPivot(&arr[low], &arr[j]);
    swapPivot(&arr[high], &arr[g]);

    // returning the indices of the pivots.
    *lp = j; // because we cannot return two elements
    // from a function.

    return g;
}

void dualPivotQuickSortRec(int arr[], int low, int high, int &compares_n, int &swaps_n) {
    DualPivotQuickSort(arr,low,high);
    compares_n = d_compares_n;
    swaps_n = d_swaps_n;
}
