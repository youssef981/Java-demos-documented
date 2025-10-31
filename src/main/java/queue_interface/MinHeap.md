### Introduction to Min-Heap
- A Min-Heap is a Data Structure with the following properties.

- It is a complete Complete Binary Tree.
- The value of the root node must be the smallest among all its descendant nodes and the same thing must be done for its left and right sub-tree also.

### Internal Implementation of Min-Heap Data Structure
- **A heap can be efficiently represented using an array.**
  - If a node is stored at index i:
  - Its left child is at index 2*i + 1.
  - Its right child is at index 2*i + 2.
  - The parent of a node at index i can be found at index [(i-1)/2].

### Operations on Min-heap Data Structure and their Implementation: 
Here are some common operations that can be performed on a Heap Data Structure,
- ***Insertion - O(log n) Time and O(n) Space***:
The insertion operation in a min-heap involves the following steps:

  - Add the new element to the end of the heap, in the next available position in the last level of the tree.
  - Compare the new element with its parent. If the parent is greater than the new element, swap them.
  - Repeat step 2 until the parent is smaller than or equal to the new element, or until the new element reaches the root of the tree.
```java
    public static void insert(ArrayList<Integer> heap, int value) {
        // Add the new element to the end of the heap
        heap.add(value);

        // Get the index of the last element
        int index = heap.size() - 1;
        // Compare the new element with
        // its parent and swap if necessary
        while (index > 0 && heap.get((index - 1) / 2) > heap.get(index)) {
            int temp = heap.get(index);
            heap.set(index, heap.get((index - 1) / 2));
            heap.set((index - 1) / 2, temp);
            // Move up the tree to the
            // parent of the current element
            index = (index - 1) / 2;
        }
```

- ***Deletion -O(long n) Time and O(n) Space***:
Removing the smallest element (the root) from a Min-Heap involves the following steps:

  - Replace the root (or the element to be deleted) with the last element in the heap.
  - Remove the last element from the heap, since it has been moved to the root.
  - Heapify-down: The element now at the root may violate the Min-Heap property, so perform heapify starting from the root to restore the heap property.
```java
    public static void deleteMin(ArrayList<Integer> heap, int value) {
        // Find the index of the element to be deleted
        int index = -1;
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i) == value) {
                index = i;
                break;
            }
        }

        // If the element is not found, return
        if (index == -1) {
            return;
        }

        // Replace the element to be deleted with the last
        // element
        heap.set(index, heap.get(heap.size() - 1));

        // Remove the last element
        heap.remove(heap.size() - 1);

        // Heapify the tree starting from the element at the
        // deleted index
        while (true) {
            int left_child = 2 * index + 1;
            int right_child = 2 * index + 2;
            int smallest = index;

            if (left_child < heap.size() && heap.get(left_child) < heap.get(smallest)) {
                smallest = left_child;
            }
            if (right_child < heap.size() && heap.get(right_child) < heap.get(smallest)) {
                smallest = right_child;
            }
            if (smallest != index) {
                int temp = heap.get(index);
                heap.set(index, heap.get(smallest));
                heap.set(smallest, temp);
                index = smallest;
            } else {
                break;
            }
        }
    }
```
- ***Heapify -O(n) Time and O(n) Space***:
The heapify operation is used to place an element in its correct position within the heap so that the heap property is maintained.
A heapify operation can also be used to create a min heap from an unsorted array. This is done by starting at the last non-leaf node and repeatedly performing the "heapify down" operation until all nodes satisfy the heap property. 
```java
    public static void heapify(int[] arr, int i, int n) {
        int smallest = i;        
        int l = 2 * i + 1;      
        int r = 2 * i + 2;  
        
        // If left child exists and is smaller than root
        if (l < n && arr[l] < arr[smallest])
            smallest = l;

        // If right child exists and is smaller than smallest so far
        if (r < n && arr[r] < arr[smallest])
            smallest = r;

        // If smallest is not root, swap and continue heapifying
        if (smallest != i) {
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            // Recursively heapify
            heapify(arr, smallest, n);
        }
    }
```













