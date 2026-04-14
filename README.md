# -ArrayList-and-LinkedList-Stack-MinHeap-Queue
Here i implement my ArrayList and LinkedList, Stack, MinHeap, Queue for University assignment
[Assignment 2 (5).pdf](https://github.com/user-attachments/files/26645332/Assignment.2.5.pdf)

## Implemented Structures

### Physical Layer (Storage)

All physical structures implement a MyList<T> interface and support generics bounded by Comparable<T>.

MyArrayList<T>: A dynamic array implementation. It features automatic resizing (doubling capacity) and provides fast random access.

MyLinkedList<T>: A doubly-linked list implementation. Each element (Node) maintains pointers to both the previous and next elements, allowing for efficient bidirectional traversal.

### Logical Layer (Access Patterns)
MyStack<T>: A Last-In-First-Out (LIFO) structure.

MyQueue<T>: A First-In-First-Out (FIFO) structure.

MyMinHeap<T>: A specialized tree-based structure that maintains the "heap property," where the smallest element is always at the root.

## Design Choices & Architecture
1. Why MyLinkedList for MyQueueEfficiency: Queues need to add to the back and remove from the front. With a doubly-linked list, both are $O(1)$.Avoids Shifting: If I used an array, removing the first element would force every other item to shift left, making it $O(n)$. Linked lists avoid this bottleneck entirely.

2. Why MyArrayList for MyStackSpeed: Stacks only interact with the "end" of the list. Adding/removing from the end of an array is $O(1)$.Cache & Memory: Arrays store data closer together in memory (cache locality), making them faster than scattered nodes. Plus, there’s no extra memory waste from storing "next" and "prev" pointers.
   
3. Why MyArrayList for MyMinHeapSpace Efficient: A heap is a complete binary tree, so I can store it in an array without "gaps." This saves space since I don't need pointers for children or parents.
