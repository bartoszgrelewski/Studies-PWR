#include <iostream>
#include <fstream>
#include <vector>


void insertionSort(int arr[], int n, int &compares_n, int &swaps_n);
void mergeSortRec(int arr[], int n, int c, int &compares_n, int &swaps_n);
void quickSortRec(int arr[], int n, int c, int &compares_n, int &swaps_n);
void dualPivotQuickSortRec(int arr[], int n, int c, int &compares_n, int &swaps_n);
void hybridSortRec(int arr[], int n, int c, int &compares_n, int &swaps_n);
void randomGenerator(int arr[], int size);
void descGenerator(int arr[], int size);
void ascGenerator(int arr[], int size);

int swaps_n;
int compares_n;


void check_if_list_is_sorted(int A[], int n){
    for(int i = 0; i < n-1; i++){
        if(A[i] > A[i+1]){
            std::cout << "List is not sorted." <<std::endl;
        }
    }
}

// UTILITY FUNCTION
// Function to print an array
void printArray(int A[], int size) {
    for (auto i = 0; i < size; i++)
        std::cout << A[i] << " ";
}



void create() {


    std::vector < int > k;
    k.push_back(1);
    k.push_back(10);
    k.push_back(100);

    std::string nameAlg = "hybridSort";

    float temp_k = 0;
    float avg_swaps = 0;
    float avg_compares = 0;

    std::string filename("hybridSortRec.csv");
    // file pointer
    std::ofstream fout;
    // opens an existing csv file or creates a new file.
    fout.open(filename, std::ios_base::app);
    fout << "Alg;n;k;AVG_Compares;AVG_Swaps;c/n;s/n;\n";
    for (int n = 100; n <= 1000; n += 100) {
        for (auto i = k.begin(); i != k.end(); i++) {

            fout <<nameAlg << ';' << n << ';' << *i;

            for(int h = 0; h < *i; h++) {
                swaps_n = 0;
                compares_n = 0;
                int arr[n];

                randomGenerator(arr, n);
                hybridSortRec(arr, 0, n - 1, compares_n, swaps_n);

                avg_compares += compares_n;

                avg_swaps += swaps_n;
            }
            temp_k = *i;
            fout << ';' << avg_compares / temp_k << ';' << avg_swaps / temp_k << ';' << (avg_compares / n / temp_k) <<
                 ';' << (avg_swaps / n / temp_k) << ";\n";
            avg_swaps = 0;
            avg_compares = 0;
        }
    }
    fout.close();
    //printArray(arr, n);
    // insertionSort(arr, n, compares_n, swaps_n);
    // std::cout<<compares_n<<" "<<n;
    // printArray(arr, n);
}


void menu(int arr[],int n) {
    int h;
    if(n<50) {
        std::cout<<"Input data: \n";
        printArray(arr, n);
        std::cout<<" \n";
    }

    std::cout<<"Choose sorting method: \n";
    std::cout<<"1.Insertion sort. \n";
    std::cout<<"2.Merge sort. \n";
    std::cout<<"3.Quick sort. \n";
    std::cout<<"4.DualPivot sort. \n";
    std::cout<<"5.Hybrid sort. \n";
    std::cin>>h;

    switch(h) {
        case 1:
            insertionSort(arr ,n , compares_n, swaps_n);
            std::cout<<"Sorted values\n";
            printArray(arr, n);
            break;
        case 2:
            mergeSortRec(arr, 0, n - 1, compares_n, swaps_n);
            std::cout<<"Sorted values\n";
            printArray(arr, n);
            break;
        case 3:
            quickSortRec(arr, 0, n - 1, compares_n, swaps_n);
            std::cout<<"Sorted values\n";
            printArray(arr, n);
            break;
        case 4:
            dualPivotQuickSortRec(arr, 0, n - 1,compares_n, swaps_n);
            std::cout<<"Sorted values\n";
            printArray(arr, n);
            break;
        case 5:
            hybridSortRec(arr, 0, n-1, compares_n, swaps_n);
            std::cout<<"Sorted values\n";
            printArray(arr, n);
            break;
    }
}

/* Driver code */
int main() {

    std::string en, type, order;
    int n,h;
    //create();

    std::cout<<"TYPE IN length of array\n";
    std::cin>>n;
    std::cout<<"Choose type of input\n";
    std::cout<<"1.List from Keyboard\n";
    std::cout<<"2.Random values without any order.\n";
    std::cout<<"3.Random values in order - ASCENDING.\n";
    std::cout<<"4.Random values in order - DESCENDING.\n";
    std::cin>>h;
    int arr[n];

    switch(h) {
        case 1:
                std::cout<<"TYPE IN n values.\n";
                for(int i=0; i<n; i++) {
                    std::cin>>arr[i];
                }
                menu(arr,n);
             break;
        case 2:
            randomGenerator(arr,n);
            menu(arr,n);
            break;
        case 3:
            ascGenerator(arr,n);
            menu(arr,n);
            break;
        case 4:
            descGenerator(arr,n);
            menu(arr,n);
            break;
        default:
            std::cout<<"Wrong token\n";
    }
    check_if_list_is_sorted(arr,n);
    return 0;
}