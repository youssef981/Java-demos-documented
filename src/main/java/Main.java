
import ch.qos.logback.core.joran.conditional.ThenAction;
import tests.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static int add(int a, int b){
        int ia=a;
        int ib=b;
        while(b!=0){
            int carry = a & b;
            a = a ^ b;
            b = carry<<1;
        }
        return a;
    }

    char getRevertedBracket(char currentBracket){
        char revertBracket = switch(currentBracket){
            case '(':
                yield ')';
            case ')':
                yield '(';
            default:
                throw new IllegalStateException("Unexpected value: " + currentBracket);
        };
        return revertBracket;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        IteratorInterfaceDemo.playIterator();
//        CollectionInterfaceDemo.playCollection();
//        PriorityQueueDemo.playPriorityQueueDemo();

//        OuterClass outerClass  = new OuterClass();
//        OuterClass.InnerClass2 innerClass2Obj = outerClass.new InnerClass2();
//        innerClass2Obj.display();
//        SomeOtherClass someOtherClass = new SomeOtherClass();
//        someOtherClass.display();
//        AnOtherClass anOtherClass = new AnOtherClass();
//        anOtherClass.display();

//        Anonymously obj = new Anonymously() {
//            @Override
//            public void doingIt() {
//                System.out.println("One use implementation on obj doingIt call event");
//            }
//        };
//        obj.doingIt();

        //but in this case how would I know the casting type. So
//        GenericsDemo genericsDemo = new GenericsDemo();
        // what if we create a generic type T or why not 2 for example that we will define at the creation of the class object for example
        GenericsDemo<Integer,String> integerGenericsDemo = new GenericsDemo<>();
        integerGenericsDemo.setValue(1);
        int value = integerGenericsDemo.getValue();
        integerGenericsDemo.setValue1("generic");
        String generic = integerGenericsDemo.getValue1();
        System.out.println(value+" "+generic);


        //next about generics is subclass class in this case there are two subcases
        // - 1. when the subclass is not generic,

        SubClass subClass = new SubClass();
        subClass.setValue1("sub class case");
        System.out.println(subClass.getValue1());
        // generic method
        integerGenericsDemo.displayWhatever(3,"appels");
        integerGenericsDemo.displayWhatever("appels",3);
        //see the row type case when the compiler by default will replace the generic by Object
        GenericsDemo genericsDemo1 = new GenericsDemo();
        genericsDemo1.getValue();
        //generics bounding
        // actually when we talk about upper bound and multi bound generics comes from the fact that even we use
        // generic typing we need a kind of control on the those generics example:
        // we only can add a generic that is a subclass of a specific parent class we can specify also with more details
        // like at the same time implementing from a specific interfaces. (see the GenericsDemo class it have multiple upper bound)
        /*
        one more thing about generics and this explains all the previous situation is the wild card case
        and this one allow for like 100% flexibility means:
        - at the level of bounding [upper, multi and lower]
        - you're not restricted to use same generic at all places for example
            public void computeList(List<? extends Number> source, List<? extends Number> destination);
          this case we can do what ever type not restricting our code to one T type.
            public <T> void computeList(List<T extends Number> source,List<T extends Number> destination)
          this case both destination and source lists should be the same type at elements (T).

          for the wild is used when ur method can work on whatever the child of the parent class
        * */

        //Enum
        // .values method
//        System.out.println(Operation.valueOf("PLUS").operate(2,2));
//        System.out.println(Operation.valueOf("MINUS").operate(2,2));
//        int x=3,y=2;
//        for(Operation o : Operation.values()){
//            System.out.println(x + o.symbol + y + " = " + o.operate(x,y));
//        }

        System.out.println(add(-2,1));
        System.out.println(2>1);

        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(-1));

        // switch return options but still can not add any additional expressions
        int val = 1;
        String outputValue = switch(val){
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "Three";
            default -> "None";
        };
        System.out.println(outputValue);

        // but we can use yield expression
        String outputValueR = switch(val){
            case 1 -> {
                System.out.println("an expression");
                yield "one";
            }
            case 2 -> "two";
            case 3 -> "Three";
            default -> "None";
        };

        System.out.println(outputValueR);


    }



    public static class lazyThread{
        private static Thread thread ;
        private void creatTheThread(){
            if(thread==null){
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("this lazy is running");
                    }
                });
            }
        }
        private void runTheThread(){
            if(thread!=null && thread.isAlive()){
                thread.start();
            }
        }
    }

    //Dead lock demo
    public static class DeadLockDemo{
        final Object lock1 = new Object();
        final  Object lock2 = new Object();

        public void method1(){
            synchronized (lock1){
                System.out.println("thread1 is holding lock1");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock2){
                System.out.println("thread1 is holding lock2");
            }
        }

        public void method2(){
            synchronized (lock2){
                System.out.println("thread2 is holding lock2");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock1){
                System.out.println("thread2 is holding lock1");
            }
        }
    }

    //starvation demo
    public static class StarvationDemo {
        public void highPriorityTask() {
            System.out.println("the current thread: " + Thread.currentThread().getName() + " is executing...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //Race Condition demo
    public static class RaceCnditionExample{
        private int i = 0;
        public void increment(){
            i++;
        }
        public int getI(){
            return i;
        }
    }

}