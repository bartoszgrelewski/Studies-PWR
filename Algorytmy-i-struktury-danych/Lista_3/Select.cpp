#include <iostream>
#include <random>

using namespace std;

int compares = 0, swaps = 0;

void printArray(int A[], int size) {
    for (auto i = 0; i < size; i++)
        std::cout << A[i] << " ";
}

void randomGenerator(int arr[], int n) {
    std::random_device rd;
    arr[n];
    std::mt19937 mt(rd());
    std::uniform_int_distribution<int> dist(1, 2*n-1);

    for (int i=0; i<n; ++i) {
        arr[i] = (int)dist(mt);
    }
}

int Select(int *v, int n, int k, int median) {
    if (n == 1 && k == 0){
        compares++;
        return v[0];
    }

    int m = (n + median-1)/median;
    int *medians = new int[m];
    for (int i=0; i<m; i++) {
            if (median*i + (median-1) < n) {
                compares++;
            int *w = v + median*i;
            for (int j0=0; j0<3; j0++) {
                int jmin = j0;
                for (int j=j0+1; j<median; j++) {
                    if (w[j] < w[jmin]) {
                        compares++;
                        jmin = j;
                    }
                }
                swap(w[j0], w[jmin]);
                swaps++;
            }
            medians[i] = w[2];
        } else {
                compares++;
            medians[i] = v[median*i];
        }
    }

    int pivot = Select(medians, m, m / 2, median);
    delete [] medians;

    for (int i=0; i<n; i++) {
        if (v[i] == pivot) {
            compares++;
            swap(v[i], v[n-1]);
            swaps++;
            break;
        }
    }

    int store = 0;
    for (int i=0; i<n-1; i++) {
        if (v[i] < pivot) {
            compares++;
            swap(v[i], v[store++]);
            swaps++;
        }
    }
    swap(v[store], v[n-1]);
    swaps++;

    if (store == k) {
        compares++;
        return pivot;
    } else if (store > k) {
        compares++;
        return Select(v, store, k, median);
    } else {
        compares++;
        return Select(v + store + 1, n - store - 1, k - store - 1, median);
    }
}

int main(){
    int *arr, n;
    int k,median=5;
    std::cout<<"Enter size of array : ";
    std::cin >> n;
    arr = new int[n];
    randomGenerator(arr,n);
    printArray(arr,n);
    std::cout<<"\n";
    std::cout<<"Enter element to order statistic: ";
    std::cin>>k;
    std::cout<<"\n";
    std::cout<<k<<"th order statistic :\n";
    std::cout << Select(arr, n, k, median) << "\n";
    std::cout<<"END for k: "<< k << "\n";
    printArray(arr,n);
}
