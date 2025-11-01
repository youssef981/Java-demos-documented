package tests;

public class AnOtherClass extends OuterClass.InnerClass{

"""
Inner classes (non-static nested classes) have an implicit reference to their enclosing outer class instance
When you extend an inner class, the child class needs that implicit outer class reference
Since AnOtherClass doesn't automatically have an OuterClass instance, you must provide one explicitly
"""
    public AnOtherClass() {
        new OuterClass().super();
    }

    public void display(){
        display1();
    }
}
