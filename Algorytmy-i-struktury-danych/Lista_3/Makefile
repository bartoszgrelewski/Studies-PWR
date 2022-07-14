all: compiled/BinarySearch compiled/DualPivotQuickSort compiled/QuickSelectSort compiled/RandomizedSelect compiled/Select

compiled/BinarySearch: BinarySearch.cpp
	g++ -std=c++17 -Wall -o compiled/BinarySearch BinarySearch.cpp

compiled/DualPivotQuickSort: DualPivotQuickSort.cpp
	g++ -std=c++17 -Wall -o compiled/DualPivotQuickSort DualPivotQuickSort.cpp

compiled/QuickSelectSort: QuickSelectSort.cpp
	g++ -std=c++17 -Wall -o compiled/QuickSelectSort QuickSelectSort.cpp

compiled/RandomizedSelect: RandomizedSelect.cpp
	g++ -std=c++17 -Wall -o compiled/RandomizedSelect RandomizedSelect.cpp

compiled/Select: Select.cpp
	g++ -std=c++17 -Wall -o compiled/Select Select.cpp

clean:
	rm *.o all