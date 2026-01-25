package dsa;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    @Override
    public @NotNull Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> trav = head;
            @Override
            public boolean hasNext() {
                return trav != null;
            }
            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }
        };
    }

    private class Node<T> {

        private T data;
        Node<T> next;
        Node<T> prev;

        Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void addFirst(T element) {
        if (isEmpty()) {
            tail = head = new Node<>(element, null, null);
        } else {
            head.prev = new Node<>(element, this.head, null);
            this.head = head.prev;
        }
        this.size++;
    }

    public void addLast(T element) {
        if (isEmpty()) {
            tail = head = new Node<>(element, null, null);
        } else {
            tail.next = new Node<>(element, null, tail);
            this.tail = tail.next;
        }
        this.size++;
    }

    public void clear() {
        Node<T> trav = this.head;
        while (trav != null) {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        this.head = this.tail = trav = null;
        this.size = 0;
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        Node<T> node = this.head.next;
        this.head.prev = this.head.next = null;
        T data = this.head.data;
        this.head.data = null;
        this.head = node;
        if (this.head != null) head.prev = null;
        else tail = null;
        this.size--;
        return data;
    }

    public T remomveLast() {
        if (isEmpty()) return null;
        Node<T> node = this.tail.prev;
        this.tail.prev = null;
        T data = this.tail.data;
        this.tail = node;
        if (this.tail != null)
            this.tail.next = null;
        else
            this.head = null;
        this.size--;
        return data;
    }

    public T remove(Node<T> node) {
        if (node == null || isEmpty()) return null;
        if (node.prev == null) return this.removeFirst();
        if (node.next == null) return this.remomveLast();
        T data = node.data;
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.prev = node.next = null;
        node.data = null;
        this.size--;
        return data;
    }

    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();
        Node<T> trav;
        int i;
        if (index < size / 2) for (i = 0, trav = head; i != index; i++) trav = trav.next;
        else for (i = this.size, trav = this.tail; i != index; i--) trav = trav.prev;
        return remove(trav);
    }

    public boolean remove(Object obj) {
        Node<T> trav = this.head;
        if (obj == null) {
            for (trav = this.head; trav.next != null; trav = trav.next) {
                if (trav.data == null) {
                    this.remove(trav);
                    return true;
                }
            }
        } else {
            for (trav = this.head; trav.next != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    this.remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        int index = 0;
        Node<T> trav = head;
        if (obj == null) {
            for (; trav != null; trav = trav.next, index++) {
                if (trav.data == null) {
                    return index;
                }
            }
        } else
            for (; trav != null; trav = trav.next, index++) {
                if (obj.equals(trav.data)) {
                    return index;
                }
            }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;

    }
}
