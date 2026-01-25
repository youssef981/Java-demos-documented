package threads_in_java;

import java.util.concurrent.CountDownLatch;

    public class TDLocalThread implements ThreadsDemo{

    private String NAME = "\nLocal Thread var demo";
        private static boolean buttonOff;

        @Override
        public void on() {
            buttonOff = true;
        }
        @Override
        public void off() {
            buttonOff = false;
        }

        @Override
        public void execute() throws Exception {
            if(buttonOff){
                demo();
            } else {
                System.out.println("\""+name()+"\""+" DEMO IS OFF");
            }
        }
    CountDownLatch latch = new CountDownLatch(2);

    @Override
    public String name() {
        return NAME;
    }


    public static final ThreadLocal<Integer> localThreadVar = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    @Override
    public void demo() throws Exception {
        System.out.println(name());
        Thread thh1 = new Thread(()->{
            for(int i=1;i<=4;i++){
                localThreadVar.set(i);
                System.out.println(Thread.currentThread().
                        getName()+"'s local variable: "+
                        localThreadVar.get());
            }
            latch.countDown();
            },"thh1");

        Thread thh2 = new Thread(()->{
            localThreadVar.set(200);
            System.out.println(Thread.currentThread().
                    getName()+"'s local variable: "+
                    localThreadVar.get());
            latch.countDown();
        },"thh2");
        thh1.start();
        thh2.start();
        thh1.join();
        thh2.join();
    }

}
