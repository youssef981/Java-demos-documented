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