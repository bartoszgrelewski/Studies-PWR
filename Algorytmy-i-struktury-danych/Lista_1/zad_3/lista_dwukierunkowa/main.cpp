// Singly linked list implementation based on Cormen - "Wprowadzenie do Algorytmów".
#include <cstdlib>
#include <iostream>
#include <chrono>

// Making a node struct containing a data int and a pointer
// to another node
struct Node {
    int data;
    Node *next;
    Node *prev;
};

class List {
public:
    // inserting elements (At start of the list)
    void insert(Node** head_ref, int new_data) {

        Node *last = (*head_ref)->prev;
        Node* new_node = new Node;
        new_node->data = new_data;
        new_node->next = *head_ref;
        new_node->prev = last;
        last->next = (*head_ref)->prev = new_node;
        *head_ref = new_node;
    }
    // inserts elements (At end of the list)
    void append(Node** head_ref, int new_data) {

        if (*head_ref == NULL) {
            Node* new_node = new Node;
            new_node->data = new_data;
            new_node->next = new_node->prev = new_node;
            *head_ref = new_node;
            return;
        }
        Node *last = (*head_ref)->prev;
        Node* new_node = new Node;
        new_node->data = new_data;
        new_node->next = *head_ref;
        (*head_ref)->prev = new_node;
        new_node->prev = last;
        last->next = new_node;
    }

    // loop over the list. return true if element found
    int search(Node* head, int val) {
        Node *temp = head;
        Node *last = head->prev;
        int flag=0;
        if(temp == nullptr)
            return -1;
        else
        {
            while(temp->next != head) {
                if(temp->data == val) {
                    flag = 1;
                    break;
                }
                temp = temp->next;
            }

            if(head->data == val) flag = 1;
            if(last->data == val) flag = 1;

            if(flag == 1)
                std::cout<<"\n"<<val <<" found  "<<std::endl;
            else
                std::cout<<"\n"<<val <<" not found"<<std::endl;
        }
    }

    void display(Node* head) {

        Node *temp = head;
        while (temp->next != head)
        {
            std::cout<<temp->data<<" ";
            temp = temp->next;
        }
       std::cout<<temp->data<<std::endl;
    }

    void test(Node* head) {
        int x;
        std::chrono::steady_clock::time_point begin = std::chrono::steady_clock::now();
        search(head,9);
        std::chrono::steady_clock::time_point end = std::chrono::steady_clock::now();

        std::cout << "TEST 1 -> Time difference  = "
                  << std::chrono::duration_cast<std::chrono::microseconds>(end - begin).count() << "[µs]" << std::endl;
        std::cout << "TEST 1 -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::nanoseconds>(end - begin).count() << "[ns]" << std::endl;

        std::chrono::steady_clock::time_point begin2 = std::chrono::steady_clock::now();
        search(head,9);
        std::chrono::steady_clock::time_point end2 = std::chrono::steady_clock::now();

        std::cout << "TEST 2 -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::microseconds>(end2 - begin2).count() << "[µs]"
                  << std::endl;
        std::cout << "TEST 2 -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::nanoseconds>(end2 - begin2).count() << "[ns]" << std::endl;

        std::chrono::steady_clock::time_point begin3 = std::chrono::steady_clock::now();
        search(head,9);
        std::chrono::steady_clock::time_point end3 = std::chrono::steady_clock::now();

        std::cout << "TEST 3 -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::microseconds>(end3 - begin3).count() << "[µs]"
                  << std::endl;
        std::cout << "TEST 3 -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::nanoseconds>(end3 - begin3).count() << "[ns]" << std::endl;

        std::chrono::steady_clock::time_point begin4 = std::chrono::steady_clock::now();
        x = std::rand() % 1000 + 1;
        search(head,x);
        std::chrono::steady_clock::time_point end4 = std::chrono::steady_clock::now();

        std::cout << "TEST 4 (random variable) -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::microseconds>(end4 - begin4).count() << "[µs]"
                  << std::endl;
        std::cout << "TEST 4 (random variable) -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::nanoseconds>(end4 - begin4).count() << "[ns]" << std::endl;

        std::chrono::steady_clock::time_point begin5 = std::chrono::steady_clock::now();
        x = std::rand() % 1000 + 1;
        search(head,x);
        std::chrono::steady_clock::time_point end5 = std::chrono::steady_clock::now();

        std::cout << "TEST 5 (random variable) -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::microseconds>(end5 - begin5).count() << "[µs]"
                  << std::endl;
        std::cout << "TEST 5 (random variable) -> Time difference = "
                  << std::chrono::duration_cast<std::chrono::nanoseconds>(end5 - begin5).count() << "[ns]" << std::endl;
    }
};

Node* merge(Node* head, Node* head_two) {
    Node *temp = nullptr;
    Node *r = nullptr;
    r = head;
    temp = head;
    while (temp->next != head)
        temp = temp->next;
    temp->next = head_two;
    temp = head_two;
    while (temp->next != head_two)
        temp = temp->next;
    temp->next = r;
    return (r);
}

int main() {
    Node* head = nullptr;
    Node* head_two = nullptr;
    Node* head_three = nullptr;

    std::srand(std::time(nullptr)); // use current time as seed for random generator
    int x;
    List l, l_two;

    for (int i = 0; i < 1000; i++) {
        x = std::rand() % 1000 + 1;  // range: 1-1000
        l.append(&head,x);
    }
    l.display(head);

    for (int i = 10; i < 1000; i++) {
        x = std::rand() % 1000 + 1;  // range: 1-1000
        l_two.append(&head_two,x);
    }
    l_two.display(head_two);

    // Test of time
    l.test(head);

    // search throught list
    //l.search(head, 0);

    // MERGE list
    head_three = merge(head, head_two);
    l.display(head_three);
}