### when we use enums
- Simply we use them in case we have a collection of fixed elements.
- Even more then that we can include a specific behaviour or sub 
elements that can each element have.

**So how we can see enums**:
- basically those they present luxury arrays that not only hold those
fixed elements but it consider them more then simple constants but also
a classes that can extend from the enum so any elements or methods declared
in the enum they'l be herited by each "constant" a simple example:

```java
package enums;

public enum Operation{
    PLUS("+") {
        @Override
        public <T extends Number> double operate(T x, T y) {
            return x.doubleValue() + y.doubleValue();
        }
    },
    MINUS("-") {
        @Override
        public <T extends Number> double operate(T x, T y) {
            return x.doubleValue() - y.doubleValue();
        }
    },
    ;
    public final String symbol;
    public abstract <T extends Number> double operate(T x, T y);
    Operation(String symbol){
        this.symbol =  symbol;
    }
}

// see line number 76 in the psvm method for its use
```

- Things we should agree about enums:
  - It has no sense if we can initiate an enum because why we would have an object 
  that hold static constants while just call the class as a result the constructor 
  is private.
  - Can not be extent by any class because it's useless again.
  - Has no variables or custom methods.
  - also enums can implement interfaces and then implemented body in the enum 
  will be common between all constants in case we do not wanna use abstract
  method and then override them in each constant.
  - what ever a variable added to the enum it should be added to the 
  constructor.