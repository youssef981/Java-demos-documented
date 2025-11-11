Journey to the perfect Singleton implementation.
==
Singleton class has only one job used for once to creat an
object that will be the one and only and will be used by 
other classes

for this there is several implementation and different ways 
but always there is a best approach to do it.

first suggested one is:
- ***Eager Initialization***:
this one is the simplest one that respect the minimum characteristics
of a singleton class which are:
  - private constructor.
  - static variable accessed from the class
  - eager creation of the object and get it by a public static method without 
  exposing the constructor.
```java
public SingletonConnection{
    private static SingletonConnection connection;
    private SingletonConnection(){
    }
    public static SingletonConnection getConnection{
        connection = new SingletonConnection();
        return connection;
    }
}
```
But this approach has some cons because what if I used it 
multiple times then we will have multiple objects so we have
to do a null test if the object already initialized to not 
over do it then we have to use:

- ***The lazy initialization***:
  - this one use a null check
```java
public SingletonConnection{
    private static SingletonConnection connection;
    private SingletonConnection(){
    }
    public static SingletonConnection getConnection{
        if(SingletonConnection.connection==null)
            connection = new SingletonConnection();
        return connection;
    }
}
```

and again we're not yet arrive to the best one this last has some
cons itself and one of them is that what this the `SingletonConnection.getConnection`
is called by two threads at the same time what will happened then
is simply we will have two objects created at the same time. so for this
we have to put a LOCK on the method itself so if a method is thread is using 
it, it will put a LOCK on it so no other thread can use it.
```java
public SingletonConnection{
    private static SingletonConnection connection;
    private SingletonConnection(){
    }
    synchronized public static SingletonConnection getConnection{
        if(SingletonConnection.connection==null)
            connection = new SingletonConnection();
        return connection;
    }
}
```
but we still can optimize it by adding a second check before locking 
cause imaging we hava 100 thread each will lock and unlock the method
which will be very slow so why not adding a second check before doing the lock
keeping the fist one before the initialization and it will be
- ***double check lock***:
```java
public SingletonConnection{
    private static SingletonConnection connection;
    private SingletonConnection(){
    }
    synchronized public static SingletonConnection getConnection{
        if(connection==null){
            synchronized (SingletonConnection.class){
                connection = new SingletonConnection();
            }
        }
        return connection;
    }
}
```
and yet there is some cons to mention, so the thread itself work 
as the next way:
 - ***Volatile variable***
    - run on the core use cache for storing object that it creates and use
and then those objs passes to the memory so even if a method used the 
synchronized method and then removed the lock after that and the creted 
object only stored in the cache and not yet passed to the memory
a second thread will use that method and same way because the null test
will not detect any ojct in the memory of first thread (still in cach) 
thread 2 now will have a second object in the cache and by the end the two
objs will pass to the memory :>, so we have to find way that if a thread 
created the Connection obj should not stored in the cache but directly 
to the memory so if thread 2 comes null test will find the obj of T1 in the
memory for that we `volatile` keyword
```java
public SingletonConnection{
    private static volatile SingletonConnection connection;
    private SingletonConnection(){
    }
    synchronized public static SingletonConnection getConnection{
        if(connection==null){
            synchronized (SingletonConnection.class){
                connection = new SingletonConnection();
            }
        }
        return connection;
    }
}
```
as we can see previously we can say that we handle the synchronization issue
between thereds manually adding the `synchronized` key word the `volatile` and 
double checking for overcoming the overlocking, and in java specification there
is this area where the jvm automatically this locking and unlocking, and this is during 
initialization, but how would we handle the multiple calling part is by nested classes
because we only have issue at the first call.
- ***Nested class way***:
```java
public class SingletonConnection{

    private SingletonConnection(){}
    
    //initialization part
    private static class HelperClass{
        private static final SingletonConnection instance;
        private void initialize(){
            this.instance = new SingletonConnection();
        }
    }
    
    //return part
    public static SingletonConnection getInstance(){
        return HelperClass.instance;
    }
    
}
```
- this by the way called the Bill pugh method.

but still the singleton using classes is not the best way using enums is 
way more thread safe due to the design of enums, also all previous 
ways to implement singleton are ont safe from reflection  in java
because simply we can access any private constructor and set to public
by then we can create as many instances (see how in the instance md file)
unlike the enums are Reflection-proof because The JVM prevents creating new 
enum instances via reflection. Any attempt to do so throws an `IllegalArgumentException`.
and this is how it works:

Thread-safe by design
   What it means:
   The Java language itself handles the safety for you. When the enum 
class is first used, the Java Virtual Machine (JVM) loads it and creates 
its instances. This loading process has a built-in lock. Only one thread 
can execute this initialization at a time. Other threads that try to use
the enum at the same moment are forced to wait until the first thread is 
finished. Once the enum instance is created, all threads simply get a 
reference to that single, already-existing object. You don't have to 
write any special code to manage threads; the guarantee is provided by 
the core language. Example:
- ***Enum approach:***
```java
public enum DatabaseConnection {
    INSTANCE;

    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/mydb";

    private DatabaseConnection() {
        try {
            // This constructor runs only once, in a thread-safe manner
            connection = DriverManager.getConnection(url, "user", "password");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database connection", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
```




