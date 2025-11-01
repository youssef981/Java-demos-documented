package test;

public class Employee extends Person {
    private int age;

    @Override
    public String toString() {
        return "Employee{ id = " + iD +
                " age" + age +
                '}';
    }

    public Employee(int iD, int age){
        super(iD);
        this.age = age;
        System.out.println("now the age is initialized.");


    }
}
