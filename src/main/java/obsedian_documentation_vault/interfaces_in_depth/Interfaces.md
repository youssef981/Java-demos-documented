Interfaces in depth
==
- ***Interfaces***: by definition in general it's a component that allow
two systems to communicate without exposing there internals to each other
just the needed functionalities.

So simply they encapsulate a certain part functionalities from that 
system and then provide them to whatever the services that needed
which is the same for interfaces on oop they capture a certain behaviour
of a class and then provide it to other classes if they have a common 
behaviour.

- ***In java***:
  - Interfaces only provide the methode definition (signature)
  - can not implement any methode unlike abstract classes they can 
  provide some implementations and make them final.
  - Also unlike in abstract classes they do not hava a constructor.
  - we can think of nested interfaces when we have a logical group of 
  methods and a class can implement the outer methods xor inner methods interface
  - all variables in interface are considered constants `final statis`,
  even we can initial a constant Exp: `boolean DEBUG = true;`
  - method in interfaces can only be `public` and by default they are
  public unlike in abstract classes we can find all modifiers we need
  - also about the speed abstract classes are way more faster due the dynamic
  resolution of methods in interfaces `Polymorphism`

So we can say that when use abstract classes or interfaces generally we would say 
that we can use interfaces when we want only to encapsulate the behaviour unlike
abstract classes but this not the only difference also we can say that it depends
also if we have for example a default behaviour to hold abstract classes will be
our way out.

here an example of nested interfaces (an interface within a class):
```java
class OuterClass {
    interface InnerInterface {
        void show();
    }
}

class Implementation implements OuterClass.InnerInterface {
    public void show() {
        System.out.println("Show method from nested interface");
    }

    public static void main(String[] args) {
        OuterClass.InnerInterface obj = new Implementation();
        obj.show();
    }
}   
```
(interface within an interface)
```java
interface OuterInterface {
    void method1();
    
    interface InnerInterface {
        void method2();
    }
}

class InnerImpl implements OuterInterface.InnerInterface {
    public void method2() {
        System.out.println("Method from inner interface");
    }

    public static void main(String[] args) {
        OuterInterface.InnerInterface obj = new InnerImpl();
        obj.method2();
    }
}   
```
- ***form java 9***
  - form java 8 methods in interface can be `default` or `private` or
  `static` or `static private`, for if a `default` is `static` too it 
  can not a class or an interface change it
  - what's cool about private methods in interface let's say you have
  5 defualt methods and they share 80% of the code this code can implemented
  in other `default private` methods and then used in one `default publid` method.
  

