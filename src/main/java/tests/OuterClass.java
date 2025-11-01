package tests;

public class OuterClass {
    int num = 1;
    static String tag = "v1";

    public class InnerClass1{
        int innerClassNum = 2;
        String innnerClassTag = "V inner1";
    }

         public class InnerClass2 extends InnerClass1{
        int innerClass2Num = 4;
        public void display(){
            System.out.println(num+tag+innerClassNum+innnerClassTag+innerClass2Num);
        }
    }

    public static class NestedClass{
        public void display(){
            System.out.println("this an extent of an nested class");
        }
    }
    public class InnerClass{
        public void display1(){
            System.out.println("this an extent of an InnerClass");
        }
    }

}
