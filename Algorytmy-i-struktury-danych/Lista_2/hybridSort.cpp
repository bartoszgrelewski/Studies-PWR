int h_compares_n;
int h_swaps_n;

void insertion_sort(int arr[], int low, int n) {

    for(int i=low+1;i<n+1;i++) {
        int val = arr[i] ;
        int j = i ;

        while (j>low && arr[j-1]>val) {
            h_compares_n += 2;
            arr[j]= arr[j-1] ;
            h_swaps_n++;
            j-= 1;
        }
        arr[j]= val ;
    }
}

//The following two functions are used
// to perform quicksort on the array.


// Partition function for quicksort

int partitionHybrid(int arr[], int low, int high)
{
    int pivot = arr[high] ;
    int i ,j;
    i = low;
    j = low;

    for (int i = low; i < high; i++)
    {
        h_compares_n++;
        if(arr[i]<pivot)
        {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            h_swaps_n++;
            j += 1;
        }
    }

    int temp = arr[j];
    arr[j] = arr[high];
    arr[high] = temp;
    h_swaps_n++;

    return j;
}


// Function to call the partition function
// and perform quick sort on the array


void quick_sort(int arr[], int low,int high)
{
    if (low < high)
    {
        int pivot = partitionHybrid(arr, low, high);
        quick_sort(arr, low, pivot-1) ;
        quick_sort(arr, pivot + 1, high) ;


    }
}

// Hybrid function -> Quick + Insertion sort

void hybridSort(int* arr, int low, int high)
{
    while (low < high)
    {

        // If the size of the array is less
        // than threshold apply insertion sort
        // and stop recursion

        if (high-low + 1 < 10)
        {
            insertion_sort(arr, low, high);
            break;
        }

        else

        {
            int pivot = partitionHybrid(arr, low, high) ;

            // Optimised quicksort which works on
            // the smaller arrays first

            // If the left side of the pivot
            // is less than right, sort left part
            // and move to the right part of the array
            h_compares_n++;
            if (pivot-low<high-pivot)
            {
                hybridSort(arr, low, pivot - 1);
                low = pivot + 1;
            }
            else
            {

                // If the right side of pivot is less
                // than left, sort right side and
                // move to the left side

                hybridSort(arr, pivot + 1, high);
                high = pivot-1;
            }
        }
    }
}
void hybridSortRec(int arr[], int low, int high, int &compares_n, int &swaps_n) {
    hybridSort(arr, low, high);
    compares_n = h_compares_n;
    swaps_n = h_swaps_n;
}