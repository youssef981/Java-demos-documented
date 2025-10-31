package collection;

import java.util.*;

public class CollectionInterfaceDemo {

    public static void playCollection(){


        // The Collection interface represents a group of elements.
        // You cannot instantiate it directly; you must use a class that implements it (like ArrayList).
        Collection<String> names = new ArrayList<>();

        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        System.out.println("Collection elements: " + names);
        System.out.println("Contains Bob? " + names.contains("Bob"));
        System.out.println("Size: " + names.size());

        // The Collections class provides static helper methods
        // to operate on or return Collection objects.

        // Sorting (works on List, not generic Collection)
        List<String> nameList = new ArrayList<>(names);
        Collections.sort(nameList);
        System.out.println("Sorted list: " + nameList);

        // Reversing
        Collections.reverse(nameList);
        System.out.println("Reversed list: " + nameList);

        // Finding max/min
        System.out.println("Max element: " + Collections.max(nameList));
        System.out.println("Min element: " + Collections.min(nameList));

        // Making the list unmodifiable
        List<String> unmodifiableList = Collections.unmodifiableList(nameList);
        System.out.println("Unmodifiable list: " + unmodifiableList);
        // unmodifiableList.add("David"); // ← throws UnsupportedOperationException

    }

}
