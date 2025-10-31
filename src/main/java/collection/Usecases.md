
Collection interface, which defines the basic behaviors shared by all collection types—such as adding, removing, and iterating over elements. From this foundation, more specialized interfaces like List, Set, Queue, and Deque extend these capabilities to handle ordered data, unique elements, or queue-based operations. Each concrete class (like ArrayList, HashSet, LinkedList, or PriorityQueue) implements these interfaces differently, optimizing for performance, ordering, or storage behavior.
### Java Collection Framework — Core Methods Overview

| **S.NO**                 | **INTERFACE / CLASS** | **METHOD**                      | **Available in** | **USAGE**                                                           |
| ------------------------ | --------------------- | ------------------------------- | ---------------- |---------------------------------------------------------------------|
| **Collection Interface** |                       |                                 |                  |                                                                     |
| 1                        | Collection            | `size()`                        | Java 1.2         | Returns total number of elements in the collection.                 |
| 2                        | Collection            | `isEmpty()`                     | Java 1.2         | Checks if the collection is empty. Returns true/false.              |
| 3                        | Collection            | `contains(Object o)`            | Java 1.2         | Checks if the collection contains a specific element.               |
| 4                        | Collection            | `toArray()`                     | Java 1.2         | Converts the collection into an array.                              |
| 5                        | Collection            | `add(E e)`                      | Java 1.2         | Inserts an element into the collection.                             |
| 6                        | Collection            | `remove(Object o)`              | Java 1.2         | Removes a single instance of the specified element.                 |
| 7                        | Collection            | `addAll(Collection c)`          | Java 1.2         | Adds all elements from another collection.                          |
| 8                        | Collection            | `removeAll(Collection c)`       | Java 1.2         | Removes all elements that are also in the specified collection.     |
| 9                        | Collection            | `clear()`                       | Java 1.2         | Removes all elements from the collection.                           |
| 10                       | Collection            | `equals(Object o)`              | Java 1.2         | Compares two collections for equality.                              |
| 11                       | Collection            | `stream()` / `parallelStream()` | Java 1.8         | Provide effective way to use collection like processing data...etc. |
| 12                       | Collection            | `iterator()`                    | Java 1.2         | Returns an iterator to traverse elements.                           |

- one more important thing what is the difference between (Collection and Collections) the difference is
  - Collection is a part of the java collection framework, an interface that is expoe various methods to various interfaces and classes to implement.
  - Collections, is a utility class that provide ***Static methods*** which ar used to **perate on collections** like, swaping, searching, reverse, copy here a list of all methods that `Collections` utility provide:

| **METHOD**                                                             | **USAGE / DESCRIPTION**                                                         |
| ---------------------------------------------------------------------- | ------------------------------------------------------------------------------- |
| `sort(List<T> list)`                                                   | Sorts the elements of the specified list in natural order.                      |
| `sort(List<T> list, Comparator<? super T> c)`                          | Sorts the list according to the specified comparator.                           |
| `reverse(List<?> list)`                                                | Reverses the order of elements in the given list.                               |
| `shuffle(List<?> list)`                                                | Randomly permutes the elements in the list.                                     |
| `shuffle(List<?> list, Random rnd)`                                    | Randomly shuffles elements using a provided random source.                      |
| `swap(List<?> list, int i, int j)`                                     | Swaps the elements at the specified positions.                                  |
| `fill(List<? super T> list, T obj)`                                    | Replaces all elements of the list with the specified value.                     |
| `copy(List<? super T> dest, List<? extends T> src)`                    | Copies all elements from one list into another.                                 |
| `min(Collection<? extends T> coll)`                                    | Returns the minimum element in a collection (natural order).                    |
| `min(Collection<? extends T> coll, Comparator<? super T> comp)`        | Returns the minimum element using a custom comparator.                          |
| `max(Collection<? extends T> coll)`                                    | Returns the maximum element in a collection (natural order).                    |
| `max(Collection<? extends T> coll, Comparator<? super T> comp)`        | Returns the maximum element using a custom comparator.                          |
| `frequency(Collection<?> c, Object o)`                                 | Returns the number of times the object appears in the collection.               |
| `disjoint(Collection<?> c1, Collection<?> c2)`                         | Checks if two collections have no elements in common.                           |
| `binarySearch(List<? extends Comparable<? super T>> list, T key)`      | Searches a sorted list for the specified element using binary search.           |
| `binarySearch(List<? extends T> list, T key, Comparator<? super T> c)` | Searches a sorted list using a custom comparator.                               |
| `indexOfSubList(List<?> source, List<?> target)`                       | Returns the starting position of the first occurrence of the specified sublist. |
| `lastIndexOfSubList(List<?> source, List<?> target)`                   | Returns the starting position of the last occurrence of the specified sublist.  |
| `unmodifiableList(List<? extends T> list)`                             | Returns an unmodifiable view of the specified list.                             |
| `unmodifiableSet(Set<? extends T> s)`                                  | Returns an unmodifiable view of the specified set.                              |
| `unmodifiableMap(Map<? extends K, ? extends V> m)`                     | Returns an unmodifiable view of the specified map.                              |
| `synchronizedList(List<T> list)`                                       | Returns a synchronized (thread-safe) list backed by the specified list.         |
| `synchronizedSet(Set<T> set)`                                          | Returns a synchronized (thread-safe) set.                                       |
| `synchronizedMap(Map<K, V> m)`                                         | Returns a synchronized (thread-safe) map.                                       |
| `emptyList()`                                                          | Returns an immutable empty list.                                                |
| `emptySet()`                                                           | Returns an immutable empty set.                                                 |
| `emptyMap()`                                                           | Returns an immutable empty map.                                                 |
| `singleton(T o)`                                                       | Returns an immutable set containing only the specified object.                  |
| `singletonList(T o)`                                                   | Returns an immutable list containing only the specified object.                 |
| `singletonMap(K key, V value)`                                         | Returns an immutable map with one key-value pair.                               |
| `nCopies(int n, T o)`                                                  | Returns an immutable list consisting of *n* copies of the specified object.     |
| `checkedList(List<T> list, Class<T> type)`                             | Returns a dynamically type-safe view of the specified list.                     |
| `checkedSet(Set<T> s, Class<T> type)`                                  | Returns a dynamically type-safe view of the specified set.                      |
| `checkedMap(Map<K,V> m, Class<K> keyType, Class<V> valueType)`         | Returns a dynamically type-safe view of the specified map.                      |

