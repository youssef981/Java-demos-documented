
### `@SafeVarargs` :
- Heap pollution is when an object variable holds a reference to an other object with different type, and one of reasons that may lead to this kind of issues are Varargs  in java that can lead to this kind of problems.
```java
public static void printLogValues(List<Integer>...logNumbers){
	Object obj = logNumbers;
	List<String> stringVlue = new ArrayList<>();
	stirngValue.add("hello");
	obj[0] = stringValue; // at this line logNumbers (Integer) will point to the first element in logNumbers array which now is a (String)
}
```
and in java we have this annotation called `@SafeVarargs` that can delete this warning of possible heap pollution in the use of variable arguments and is only used with static or final methods that cannot be overridden  

from java 9 we  can use it with private methods too.

Note: this annotation `@SafeVarargs` cannot be used with non final or non static methods because they can be extent by child classes that we are not sure about  there behavior in other methods they can cause heap pollution which not the case of this annotation is meant to be: "1. **Type safety guarantee**: `@SafeVarargs` makes a **strong promise** that the method won't cause heap pollution"

example 
```java
class Dangerous<T> {
    private T[] internalArray;
    
    // Suppose this was allowed (it's not):
    @SafeVarargs
    public void addAll(T... elements) {
        // Even if THIS implementation is safe...
        internalArray = elements;  // Sharing array reference
    }
}

class EvilChild extends Dangerous<String> {
    @Override
    public void addAll(String... elements) {
        super.addAll(elements);
        // Now internalArray points to the varargs array
        // Other code might corrupt it
    }
    
    public void corrupt() {
        Object[] objArray = (Object[]) internalArray;
        objArray[0] = 42;  // Heap pollution!
    }
}
```

### Meta annotation annotation over the annotation:

Basically inside the `@Override` annotation is written this way 
```java 
import java.lang.annotation.*;

// Define where our annotation can be used
@Target({
    ElementType.TYPE,           // Classes, interfaces, enums
    ElementType.METHOD,         // Methods
    ElementType.FIELD,          // Fields
    ElementType.PARAMETER,      // Method parameters
    ElementType.CONSTRUCTOR,    // Constructors
    ElementType.LOCAL_VARIABLE, // Local variables
    ElementType.ANNOTATION_TYPE,// Other annotations
    ElementType.PACKAGE,        // Packages
    ElementType.TYPE_PARAMETER, // Type parameters (Java 8+)
    ElementType.TYPE_USE        // Any type use (Java 8+)
})
@Retention(RetentionPolicy.Method)
public @interface Override {
    String value() default "";
}
```

one  more Meta- annotation is the `@Retention` to define how annotation will be stored in java, and we have three policies:
- `RetentionPolicy.SOURCE`: Annotation will be discard by the compiler itself and will not make it the .class file 
- `RetentionPolicy.CLASS`: Annotation will be recorded in the .class file but it will be ignored by the JVM at the run time.
- `RetentionPolicy.RUNTIME`: Annotation will make to the run time and then we will use the java reflection to use them for proxing objects and add advice before after or around objects.
an example of this is the next one:
- case1
```java
// here I have created an annotation called MyCustomAnnotaion
@Retention(RetentionPolicy.RunTime)
@Target(Element.TYPE)
public @interface MyCustomAnnotation{
}
```
- case2
```java
// here I have created an annotation called MyCustomAnnotaion
@Retention(RetentionPolicy.Source)
@Target(Element.TYPE)
public @interface MyCustomAnnotation{
}
```
```java 
@MycustomAnnotaion 
public class AnnotationTest{}
```

```java 
public class Main{
	public static void main(){
		System.out.println(new MyCustomAnnotation.getClass().getAnnotation(MyCustomAnnotation.class)
	}
	// case1 will get MyCustomAnnotaion()
	// case2 will get null
}
```

==One of other meta-annotation are `@Documented` is to include annotation in the generated java documentation. Also there is `@Inherited` to make sure that the child classes also have this annotation obviously the `@SafeVarargs` annotation does not have it.==
