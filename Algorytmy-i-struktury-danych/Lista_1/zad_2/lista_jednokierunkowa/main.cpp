// Singly linked list implementation based on Cormen - "Wprowadzenie do Algorytmów".
#include <cstdlib>
#include <iostream>
#include <chrono>

// Making a node struct containing a data int and a pointer
// to another node
struct Node {
    int data;
    Node *next;
};

class List {
    // Head pointer
    Node *head;

public:
    List() {
        head = nullptr;
    }

    // inserting elements (At start of the list)
    void insert(int val) {
        // make a new node
        Node *new_node = new Node;
        new_node->data = val;
        new_node->next = nullptr;

        // If list is empty, make the new node, the head
        if (head == nullptr)
            head = new_node;
            // else, make the new_node the head and its next, the previous
            // head
        else {
            new_node->next = head;
            head = new_node;
        }
    }

    // loop over the list. return true if element found
    bool search(int val) {
        Node *temp = head;
        while (temp != nullptr) {
            if (temp->data == val)
                return true;
            temp = temp->next;
        }
        return false;
    }

    void remove(int val) {
        // If the head is to be deleted
        if (head->data == val) {
            delete head;
            head = head->next;
            return;
        }

        // If there is only one element in the list
        if (head->next == nullptr) {
            // If the head is to be deleted. Assign null to the head
            if (head->data == val) {
                delete head;
                head = nullptr;
                return;
            }
            // else print, value not found
            std::cout << "Value not found!" << std::endl;
            return;
        }

        // Else loop over the list and search for the node to delete
        Node *temp = head;
        while (temp->next != nullptr) {
            // When node is found, delete the node and modify the pointers
            if (temp->next->data == val) {
                Node *temp_ptr = temp->next->next;
                delete temp->next;
                temp->next = temp_ptr;
                return;
            }
            temp = temp->next;
        }

        // Else, the value was neve in the list
        std::cout << "Value not found" << std::endl;
    }

    void display() {
        Node *temp = head;
        while (temp != nullptr) {
            std::cout << temp->data << " ";
            temp = temp->next;
        }
        std::cout << std::endl;
    }

    void test() {
        int x;
        std::chrono::steady_clock::time_point begin = std::chrono::steady_clock::now();
        search(7);
        std::chrono::steady_clock::time_point end = std::chrono::steady_clock::now();

        std::cout << "TEST 1 -> Time difference  = "
                  << std::chrono::duration_cast<std::chrono::microseconds>(end - begin).count() << "[µs]" << std::endl;
        std::cout << "TEST 1 -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::nanoseconds>(end - begin).count() << "[ns]" << std::endl;


        std::chrono::steady_clock::time_point begin2 = std::chrono::steady_clock::now();
        search(8);
        std::chrono::steady_clock::time_point end2 = std::chrono::steady_clock::now();

        std::cout << "TEST 2 -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::microseconds>(end2 - begin2).count() << "[µs]"
                  << std::endl;
        std::cout << "TEST 2 -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::nanoseconds>(end2 - begin2).count() << "[ns]" << std::endl;

        std::chrono::steady_clock::time_point begin3 = std::chrono::steady_clock::now();
        search(9);
        std::chrono::steady_clock::time_point end3 = std::chrono::steady_clock::now();

        std::cout << "TEST 3 -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::microseconds>(end3 - begin3).count() << "[µs]"
                  << std::endl;
        std::cout << "TEST 3 -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::nanoseconds>(end3 - begin3).count() << "[ns]" << std::endl;

        std::chrono::steady_clock::time_point begin4 = std::chrono::steady_clock::now();
        x = std::rand() % 1000 + 1;
        search(x);
        std::chrono::steady_clock::time_point end4 = std::chrono::steady_clock::now();

        std::cout << "TEST 4 (random variable) -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::microseconds>(end4 - begin4).count() << "[µs]"
                  << std::endl;
        std::cout << "TEST 4 (random variable) -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::nanoseconds>(end4 - begin4).count() << "[ns]" << std::endl;
    }

    static void merge(List &l, List &l_two) {
        if (l.head == nullptr) {
            l = l_two;
        } else {
            Node *temp = l.head;
            while (temp->next != nullptr) {
                temp = temp->next;
            }
            temp->next = l_two.head;
        }
    }
};

int main() {

    std::srand(std::time(nullptr)); // use current time as seed for random generator
    int x;
    List l, l_two;
    for (int i = 0; i < 1000; i++) {
        x = std::rand() % 1000 + 1;  // range: 1-1000
        l.insert(x);
    }
    for (int i = 0; i < 1000; i++) {
        x = std::rand() % 1000 + 1;  // range: 1-1000
        l_two.insert(x);
    }
    std::cout<<"First list"<<std::endl;
    l.display();
    std::cout<<"Second list"<<std::endl;
    l_two.display();
    std::cout<<std::endl;

    // remove
    std::cout<<"I'm deleting value number 1"<<std::endl;
    l.remove(1);
    l.display();

    // Test of time
    l.test();

    // search throught list
    std::cout<<"Searching element 7 throught first list"<<std::endl;
    std::cout << l.search(7) << std::endl;

    // MERGE list
    List::merge(l, l_two);
    std::cout<<"Merge list one and two"<<std::endl;
    l.display();
}