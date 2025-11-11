Functional Interface and Lambda Expression - Java8 features
==
A functional interface s by definition an interface tha contains a single 
single abstract method (SAM) and (it can have default, static private) methods
but.

And one of the ways that we can use to implement a functional interface is using
lambda expressions, why those ones are linked.
```java
@FunctionalInterface
public interface Sam{
    void someBehaviour(String s);
}

public static void main() {
    Sam sam = (String s) -> {System.out.println(s);};
    sam.someBehaviour("weird");
}
```
some thing else is that those functional interfaces usually they have
one specific job, and in this case in the majority of cases those methods
are called on the flight and not implemented by a specific class mostly are
utility methods called in a specific event which explains why the lambda way
is way more easier to do it anonymously.

- ***BTW there is several built in SAMs***:
  - Consumer: it gets one parameter and return nothing `void accept(T t);`
  - supplier: take no parameter and return `T get();`
  - function:  take and return `K apply(T t);`
  - predicate: take and return boolean `boolean test(T t);`
