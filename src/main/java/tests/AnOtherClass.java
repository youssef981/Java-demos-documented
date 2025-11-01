package tests;

public class AnOtherClass extends OuterClass.InnerClass{
    public AnOtherClass() {
        new OuterClass().super();
    }

    public void display(){
        display1();
    }
}
