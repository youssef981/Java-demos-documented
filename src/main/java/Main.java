import collection.CollectionInterfaceDemo;
import iterator.IteratorInterfaceDemo;
import queue_interface.PriorityQueueDemo;
import test.Employee;
import test.OuterClass;

import javax.swing.plaf.metal.MetalPopupMenuSeparatorUI;
import java.lang.classfile.attribute.SourceDebugExtensionAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        IteratorInterfaceDemo.playIterator();
//        CollectionInterfaceDemo.playCollection();
//        PriorityQueueDemo.playPriorityQueueDemo();
        OuterClass outerClass  = new OuterClass();
        OuterClass.InnerClass2 innerClass2Obj = outerClass.new InnerClass2();
        innerClass2Obj.display();

    }
}
