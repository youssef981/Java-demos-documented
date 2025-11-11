Reflection in java
==
### The Problem

A **Singleton** ensures that only one instance of a class exists during the lifetime of an application.
However, the **classic Singleton implementations** in Java (using private constructors and static instance fields) can be **broken by reflection** — because reflection can access private constructors.

Example of a vulnerable singleton:

```java
public class ClassicSingleton {
    private static final ClassicSingleton INSTANCE = new ClassicSingleton();

    private ClassicSingleton() {
        if (INSTANCE != null) {
            throw new RuntimeException("Use getInstance() method to get the singleton instance");
        }
    }

    public static ClassicSingleton getInstance() {
        return INSTANCE;
    }
}
```

Now, this can be **broken by reflection**:

```java
ClassicSingleton instance1 = ClassicSingleton.getInstance();

Constructor<ClassicSingleton> constructor =
        ClassicSingleton.class.getDeclaredConstructor();
constructor.setAccessible(true);
ClassicSingleton instance2 = constructor.newInstance();

System.out.println(instance1 == instance2); //false
```

Even though we tried to guard against it in the constructor, reflection bypasses the `private` access control.

### The Enum-Based Singleton (the real safe way)

The **Enum Singleton pattern** is **the only reflection-proof, serialization-proof, and thread-safe** singleton implementation in Java.

```java
public enum EnumSingleton {
    INSTANCE;

    public void doSomething() {
        System.out.println("Doing something...");
    }
}
```

Usage:

```java
EnumSingleton singleton = EnumSingleton.INSTANCE;
singleton.doSomething();
```

### Why Enum Singleton is Safe

1. **Reflection-proof:**
   The JVM prevents creating new enum instances via reflection.
   Any attempt to do so throws an `IllegalArgumentException`.

2. **Serialization-proof:**
   Enum constants are handled specially by the JVM — during deserialization, the same instance is returned automatically. No need to implement `readResolve()`.

3. **Thread-safe:**
   The JVM guarantees that enum instances are created safely in a thread-safe manner.

4. **Simplicity:**
   No synchronization, no double-checked locking, no special code — just one line of enum definition.


### Conclusion

> There’s **no way to completely protect a traditional singleton** from reflection, **except** by using the **enum-based singleton**.

That’s why *Effective Java (Joshua Bloch)* — the book written by one of Java’s creators — explicitly recommends:

> *“A single-element enum type is the best way to implement a singleton.”*