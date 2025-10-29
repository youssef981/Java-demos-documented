import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> values = new ArrayList<>();
        values.add(1);
        values.add(2);
        values.add(3);
        values.add(4);

        Iterator<Integer> valuesIterator = values.iterator();
        // make sure you should not use the next() meth more then once in a loop cause you will jump values
        // remove() meth will pop the last element returned by the iterator.

        while (valuesIterator.hasNext()) {
            int val = valuesIterator.next();
            if (val == 3) {
                valuesIterator.remove();
            }
            System.out.println(val);
        }

        System.out.println("======");
        for(int val : values){
            System.out.println(val);
        }


        System.out.println("Hello, Simo");
    }
}
