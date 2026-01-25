package threads_in_java;

import java.util.concurrent.CountDownLatch;

public class TDSynchronizedWaitNotif implements ThreadsDemo{

    private final String NAME = "\nSynchronized wait notif demo";
    CountDownLatch latch = new CountDownLatch(2);

    synchronized void m1(){
        for(int i = 1;i<=4;i++){
            try {
                wait(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread-name: "+Thread.currentThread().getName()+"-->"+i);
            if(i==4) System.out.println("done with Thread A");
        }
        }
    synchronized void m2(){
        for(int i=1;i<=20;i++){
            System.out.println("Thread-name: "+Thread.currentThread().getName()+ "-->" + i);
            try {
                if(i%5==0) notify();
                if(i==20) System.out.println("done with Thread B");
                wait(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void execute() throws Exception {
        System.out.println(name());
        Thread t1 = new Thread(() -> {
            m1();
            latch.countDown();
        },"Thread1");
        Thread t2 = new Thread(() -> {
            m2();
            latch.countDown();
        },"Thread2");
        t1.start() ;
        t2.start();
        t1.join();
        t2.join();
    }
}
