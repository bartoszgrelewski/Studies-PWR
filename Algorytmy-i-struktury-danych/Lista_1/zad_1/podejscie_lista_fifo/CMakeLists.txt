cmake_minimum_required(VERSION 3.21)
project(podejscie_lista)

set(CMAKE_CXX_STANDARD 14)

add_executable(podejscie_lista main.cpp)


#include <iostream>

/**
* struct which has
* field next - it's a pointer
* and second field - data
*/
struct fifoListElements {
fifoListElements *next;
fifoType data;
};

/**
*
* @return !head - it means that FIFO is empty
*/
bool fifoIsEmpty ( void )
{
return !start;
}

/**
* Reading FIFO
* @return endValue - it's special number which ends program
*/
fifoType front( )
{
if( start )
return start->data;
else
return endValue;
}

/**
* This method enqueue - it adds element to list
* @param p - pointer of list's element
* @param v - data
*/
void enqueue ( fifoType v )
{
fifoListElements * p = new fifoListElements;
p->next = NULL;
p->data = v;
if( end ) end->next = p;
else     start = p;
end = p;
}

void queue::dequeue(void)
{
if( start )
{
fifoListElements * p = start;
start = start->next;
if( !start ) end = NULL;
delete p;
}
}

class queue
{
private:
fifoListElements * start;
fifoListElements * end;

public:
queue( );      // konstruktor
~queue( );     // destruktor
bool fifoIsEmpty ( void );
int  front ( void );
void enqueue ( int v );
void dequeue ( void
};

int main() {
queue Q; // kolejka

for(int i = 1; i <= 10; i++ )
Q.enqueue ( i );

while(!Q.fifoIsEmpty())
{
std::cout << Q.front() << std::endl;
Q.dequeue();
}
return 0;
}