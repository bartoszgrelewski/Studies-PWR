// Queue implementation
#include <iostream>

const int MAXINT = -2147483647;

/**
 * struct which has
 * field next - it's a pointer
 * and second field - data
 */
struct fifoListElements {
    fifoListElements *next;
    int data;
};

class queue {
private:
    fifoListElements *start;
    fifoListElements *end;

public:
    queue() {
        start = end = NULL;
    }
    ~queue() {
        while(start) dequeue();
    }
    bool fifoIsEmpty (void);
    int  front (void);
    void enqueue (int);
    void dequeue (void);
};
/**
 * @return !head - it means that FIFO is empty
 */
bool queue::fifoIsEmpty ( void )
{
    return !start;
}

/**
 * Reading FIFO
 * @return endValue - it's special number which ends program
 */
int queue::front(void) {

    if(start)
        return start->data;
    else
        return MAXINT;
}

/**
 * This method enqueue - it adds element to list
 * @param p - pointer of list's element
 * @param v - data
 */
void queue::enqueue (int v) {
    fifoListElements *p = new fifoListElements;
    p->next = nullptr;
    p->data = v;
    if(end) end->next = p;
    else     start = p;
    end = p;
}

void queue::dequeue(void) {
    if(start) {
        fifoListElements * p = start;
        start = start->next;
        if(!start) end = NULL;
        delete p;
    }
};

int main() {
    queue Q; // kolejka

    for(int i = 1; i <= 100; i++ )
        Q.enqueue ( i );

    for(int i = 1; i <= 10; i++ )
        Q.dequeue();

    while(!Q.fifoIsEmpty()) {
        std::cout << Q.front() << " ";
        Q.dequeue();
    }
    return 0;
}