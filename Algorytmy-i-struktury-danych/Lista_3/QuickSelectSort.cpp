#include <iostream>
#include <random>

int swaps = 0;
int compares = 0;

int select_Select(int* nums, int left, int right, int k, int size, int divide_size);
void select_swap(int* nums, int index1, int index2);
int select_partition(int* list, int left, int right, int pivotIndex, int n);
int select_partition_small(int* nums, int left, int right);
void copy(int* arr1, int* arr2, int n);
void randomGenerator(int arr[], int n);

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

void select_swap(int* nums, int index1, int index2){
    int temp = nums[index1];
    nums[index1] = nums[index2];
    nums[index2] = temp;
    swaps += 3; //
}

int select_partition(int* list, int left, int right, int pivotIndex, int n){
    int pivotValue = list[pivotIndex];
    select_swap(list, pivotIndex, right); // move pivot to an end
    int storeIndex = left;
    // move all elements smaller than the pivot to the left of the pivot;
    for (int i = left; i <= right-1; i++){
        compares+=2;
        if (list[i] < pivotValue){
            select_swap(list, storeIndex, i);
            storeIndex++;
        }
    }
    // Move all elements equal to the pivot right after the smaller elements
    int storeIndexEq = storeIndex;
    for (int i = storeIndex; i <= right-1; i++){
        compares+=2;
        if(list[i] == pivotValue){
            select_swap(list, storeIndexEq, i);
            storeIndexEq++;
        }
    }
    select_swap(list, right, storeIndexEq); // Move pivot to its final place
    // Return location ofpivot considering the desired locaiton n
    compares++;
    if (n < storeIndex) return storeIndex;
    compares++;
    if (n <= storeIndexEq) return n;
    return storeIndexEq;
}

int select_partition_small(int* nums, int left, int right){
    int i = left+1;
    compares++;
    while (i <= right){
        int j = i;
        compares+=2;
        while ( j > left && nums[j-1] > nums[j]){
            select_swap(nums, j-1, j);
            j--;
            compares+=2;
        }
        i++;
        compares++;
    }
    return (left+right)/2;
}

int select_pivot(int* nums, int left, int right, int size, int divide_size){
    // for 5 or less elements just get median
    compares++;
    if (right - left < divide_size) return select_partition_small(nums, left, right);
    // otherwise move the medians of five-element subgroups to the first n/5 positions
    for ( int i = left; i <= right; i+= divide_size){
        compares++;
        // get the median of the i'th five element subgroup
        int subRight = i+divide_size-1;
        compares++;
        if (subRight > right) subRight = right;

        int median5 = select_partition_small(nums, i, subRight);
        select_swap(nums, median5, left + (i-left) / divide_size);
    }
    int mid = (right - left) / (divide_size*2) + left + 1;
    return select_Select(nums, left, left + (right-left)/divide_size, mid, size, divide_size);
}

int select_Select(int* nums, int left, int right, int k, int size, int divide_size){
    compares++;
    while (true){
        compares++;
        if (left == right) return left;

        int pivot_index = select_pivot(nums, left, right, size, divide_size);
        pivot_index = select_partition(nums, left, right, pivot_index, k);
        if (size < 50){
            printArray(nums, size);
        }

        compares++;
        if (k==pivot_index){
            return k;
        } else if (k < pivot_index){
            compares++;
            right = pivot_index - 1;
        } else {
            compares++;
            left = pivot_index + 1;
        }
    }
}

int Partition(int* nums, int low, int high){
    int pivot_index = select_pivot(nums, low, high, 100, 5);
    select_swap(nums, low, pivot_index);
    int pivot = nums[low];
    swaps++; // slyszalem ze takie cos tez zapisujemy
    int leftwall = low+1;
    int temp; // only to swap

    for(int i = low + 1; i <= high; i++){
        compares+=2; // warunek fora
        if (nums[i] < pivot) {
            swaps++; // jednoczesnie dobieramy wartosc z tablicy i porownujemy
            swap(&nums[i], &nums[leftwall]);
            leftwall++;
            swaps++; // swap to 3 zmiany listy
        }
    }
    temp = pivot;
    nums[low] = nums[leftwall-1];
    nums[leftwall-1] = pivot;
    swaps+=2;

    return leftwall-1;
}

void quick_sort(int* nums, int n, int low, int high){
    compares++;
    if (low >= high){
        return;
    }
    int copy_nums[n];
    copy(copy_nums, nums, n);
    int pivot_location = Partition(nums, low, high);
    if (n < 50)
        printArray(nums, n);
    quick_sort(nums, n, low, pivot_location-1);
    quick_sort(nums, n, pivot_location+1, high);
}

void sort(int* nums, int n){
    quick_sort(nums, n, 0, n-1);
}

void copy(int* arr1, int* arr2, int n){
    for(int i = 0; i< n ; i++){
        arr1[i] = arr2[i];
    }
}

int main(){
    std::cout << "Enter size of array: " << std::endl;
    int n;
    std::cin >> n;
    int arr[n];
    randomGenerator(arr, n);
    if(n < 50){
        std::cout << "Before sorting:" << std::endl;
        printArray(arr,n);
        std::cout << "\nDuring sorting:" << std::endl;
    }
    sort(arr, n);
    if (n < 50){
        std::cout << "\nAfter sorting:" << std::endl;
        printArray(arr, n);
    }
    std::cout<<'\n';
    std::cout << "Compares, swaps: " << std::endl;
    std::cout << compares << " " << swaps << std::endl;
}