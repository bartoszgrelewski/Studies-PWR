cmake_minimum_required(VERSION 3.21)
project(Lista_2)

set(CMAKE_CXX_STANDARD 17)

add_executable(main main.cpp insertionSort.cpp mergeSort.cpp quickSort.cpp randomGenerator.cpp descGenerator.cpp dualPivotQuickSort.cpp ascGenerator.cpp hybridSort.cpp)
target_link_libraries(main)
