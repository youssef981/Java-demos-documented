package tests;

public class AnOtherClass extends OuterClass.InnerClass{

"""
Inner classes (non-static nested classes) have an implicit reference to their enclosing outer class instance
When you extend an inner class, the child class needs that implicit outer class reference
Since AnOtherClass doesn't automatically have an OuterClass instance, you must provide one explicitly
"""
    public AnOtherClass() {
        // as you know, when child class constructor invoked, it first invoked the constructor of the parent.
        // but here the parent is Inner class, so it can only be accessed by the object f the outerclass only.
        new OuterClass().super();
    }

    public void display(){
        display1();
    }
}
