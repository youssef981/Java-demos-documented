import enums.Operation;
import tests.*;
import collection.CollectionInterfaceDemo;
import iterator.IteratorInterfaceDemo;
import queue_interface.PriorityQueueDemo;

import javax.swing.plaf.metal.MetalPopupMenuSeparatorUI;
import java.lang.classfile.attribute.SourceDebugExtensionAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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

    public static void main(String[] args) {
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
//        for(Operation o:Operation.values()){
//            System.out.println(x + o.symbol + y + " = " + o.operate(x,y));
//        }

        System.out.println(add(-2,1));
        System.out.println(2>1);

        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(-1));


    }
}
