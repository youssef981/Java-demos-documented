package dsa;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.LinkedList;

public class Stack<T> implements Iterable<T> {

    private java.util.LinkedList <T> list = new java.util.LinkedList<T>();

    public void  push(T data){
        list.addLast(data);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public T pop(){
        if(isEmpty()) throw new java.util.EmptyStackException();
        else return list.removeLast();
    }

    public int size(){
        return list.size();
    }

    public Stack(T data) {
        push(data);
    }

    public T peek(){
        return list.peek();
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return list.iterator() ;
    }
}
