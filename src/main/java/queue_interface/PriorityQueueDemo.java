package queue_interface;

import java.util.*;

public class PriorityQueueDemo {

    public static void playPriorityQueueDemo() {
        System.out.println("=== MIN PRIORITY QUEUE DEMO ===");
        demonstrateMinPriorityQueue();

        System.out.println("\n=== MAX PRIORITY QUEUE DEMO ===");
        demonstrateMaxPriorityQueue();

        System.out.println("\n=== COMMON METHODS COMPARISON ===");
        demonstrateCommonMethods();
    }

    public static void demonstrateMinPriorityQueue() {
        // Creating a min priority queue (natural ordering)
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();

        // Adding elements using add() and offer()
        System.out.println("Adding elements to min PQ...");
        minPQ.add(5);
        minPQ.offer(2);
        minPQ.add(8);
        minPQ.offer(1);
        minPQ.add(3);

        System.out.println("Min PQ after additions: " + minPQ);

        // Peek - view the head without removing
        System.out.println("Peek (head element): " + minPQ.peek());

        // Poll - remove and return the head
        System.out.println("Poll (remove head): " + minPQ.poll());
        System.out.println("Queue after poll: " + minPQ);

        // Remove specific element
        System.out.println("Remove element '5': " + minPQ.remove(5));
        System.out.println("Queue after removing 5: " + minPQ);

        // Check size and emptiness
        System.out.println("Size: " + minPQ.size());
        System.out.println("Is empty: " + minPQ.isEmpty());

        // Iterate through elements (note: iteration doesn't guarantee order)
        System.out.print("Iterating through min PQ: ");
        minPQ.forEach(element -> System.out.print(element + " "));
        System.out.println();

        // Demonstrate ordering by polling all elements
        System.out.print("Polling all elements in order: ");
        while (!minPQ.isEmpty()) {
            System.out.print(minPQ.poll() + " ");
        }
        System.out.println();
    }

    public static void demonstrateMaxPriorityQueue() {
        // Creating a max priority queue using custom comparator
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b - a);
        // Alternative ways to create max PQ:
        // PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        // PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> b.compareTo(a));

        System.out.println("Adding elements to max PQ...");
        maxPQ.add(5);
        maxPQ.offer(2);
        maxPQ.add(8);
        maxPQ.offer(1);
        maxPQ.add(3);

        System.out.println("Max PQ after additions: " + maxPQ);

        // Peek - view the head without removing
        System.out.println("Peek (head element): " + maxPQ.peek());

        // Poll - remove and return the head
        System.out.println("Poll (remove head): " + maxPQ.poll());
        System.out.println("Queue after poll: " + maxPQ);

        // Remove specific element
        System.out.println("Remove element '2': " + maxPQ.remove(2));
        System.out.println("Queue after removing 2: " + maxPQ);

        // Check contains
        System.out.println("Contains 3: " + maxPQ.contains(3));
        System.out.println("Contains 10: " + maxPQ.contains(10));

        // Clear the queue
        maxPQ.clear();
        System.out.println("After clear - Is empty: " + maxPQ.isEmpty());
        System.out.println("After clear - Size: " + maxPQ.size());
    }

    public static void demonstrateCommonMethods() {
        PriorityQueue<String> minStringPQ = new PriorityQueue<>();
        PriorityQueue<String> maxStringPQ = new PriorityQueue<>(Collections.reverseOrder());

        // Add elements
        String[] words = {"apple", "zebra", "banana", "cat"};

        for (String word : words) {
            minStringPQ.offer(word);
            maxStringPQ.add(word);
        }

        System.out.println("Min String PQ: " + minStringPQ);
        System.out.println("Max String PQ: " + maxStringPQ);

        // Demonstrate difference between poll() and remove()
        System.out.println("\n--- Poll vs Remove Demonstration ---");

        System.out.println("Min PQ peek: " + minStringPQ.peek());
        System.out.println("Poll from min PQ: " + minStringPQ.poll());
        System.out.println("Min PQ after poll: " + minStringPQ);

        System.out.println("Remove 'cat' from min PQ: " + minStringPQ.remove("cat"));
        System.out.println("Min PQ after remove: " + minStringPQ);

        // Demonstrate what happens when queue is empty
        PriorityQueue<Integer> emptyPQ = new PriorityQueue<>();
        System.out.println("\n--- Empty Queue Behavior ---");
        System.out.println("Peek on empty queue: " + emptyPQ.peek()); // returns null
        System.out.println("Poll on empty queue: " + emptyPQ.poll()); // returns null

        try {
            emptyPQ.remove(); // remove() throws exception on empty queue
        } catch (NoSuchElementException e) {
            System.out.println("remove() on empty queue throws: " + e.getClass().getSimpleName());
        }

        // Demonstrate element() vs peek()
        System.out.println("\n--- element() vs peek() ---");
        PriorityQueue<Integer> testPQ = new PriorityQueue<>();
        testPQ.add(10);
        testPQ.add(20);

        System.out.println("peek(): " + testPQ.peek()); // returns null if empty
        System.out.println("element(): " + testPQ.element()); // throws exception if empty

        testPQ.clear();
        System.out.println("peek() on empty: " + testPQ.peek()); // null
        try {
            System.out.println("element() on empty: " + testPQ.element());
        } catch (NoSuchElementException e) {
            System.out.println("element() on empty throws: " + e.getClass().getSimpleName());
        }
    }
}
