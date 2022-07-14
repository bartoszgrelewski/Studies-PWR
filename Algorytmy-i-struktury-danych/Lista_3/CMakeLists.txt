cmake_minimum_required(VERSION 3.21)  # CMake version check
project(Lista_3)               # Create project "simple_example"
set(CMAKE_CXX_STANDARD 17)            # Enable c++14 standard

# Add main.cpp file of project root directory as source file
set(SOURCE_FILES DualPivotQuickSort.cpp)

# Add executable target with source files listed in SOURCE_FILES variable
add_executable(DualPivotQuickSort DualPivotQuickSort.cpp)