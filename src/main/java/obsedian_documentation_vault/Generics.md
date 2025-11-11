## Generics in Java
- ***Generics wild cards***:

Wild cards allow us for more flexibility with kind if type safety using for this 
upper, lower and unbound generics
  1. upper bounding: `<? extends ParentClass>` this a allow for only using generics
that are a subclass of the `ParentClass`.
2. Lower bounding `<? super Subclass>` this one allow for any generic in one condition 
it should be a parent of certain subclass and what is special about this one is that the 
`Object` class it will be always allowed.
3. unbound: <?> explains itself mostly used with methods that use the `Object` class methods
which make sense.
#### Why Generics

#### Generics in classes, Subclasses and methods

- ***Case 1: when the subclass does not have a generic type***:

```java
public class ParentGenericClass<T> {}
````
In this case you have to define the type of the generic type by default in this example 
bellow is `Integer`:
```java
public class SubNonGenericClass extends ParentGenericClass<Integer> {}
```
In the next example the subclass it has a generic type (in this case 
the generic type should be extent from the parent class) and of course the 
subclass can add its generic.
```java
public class SubNonGenericClass<T,U> extends ParentGenericClass<T> {}
```
the same can be applied to methods:
```java
public static <T> void generecMethod(T v);
```