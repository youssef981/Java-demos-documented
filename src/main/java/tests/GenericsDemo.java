package tests;

import java.lang.classfile.constantpool.FieldRefEntry;

public class GenericsDemo<T extends Comparable<T> ,S> {

    //previously we have to use just Object class to apply generics

    T object;
    S generic;

    public void setValue(T value){
        this.object = value;
    }

    public T getValue(){
        return this.object;
    }
    public void setValue1(S generic){
        this.generic = generic;
    }

    public S getValue1(){
        return this.generic;
    }

    //Method with generic type:
    public <F,G> void displayWhatever(F first, G second){
        System.out.println(first +" "+second);
    }
}
