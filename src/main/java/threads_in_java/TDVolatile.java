package threads_in_java;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TDVolatile implements ThreadsDemo{

    private static String NAME = "\nVolatile varilbe Demo";
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

    @Override
    public String name() {
        return NAME;
    }

    private void stopThread() {
        System.out.println("running swiched to "+running+" by the \""+Thread.currentThread().getName()+"\" thread");
        running = false;
    }

    private volatile boolean running = true;

    @Override
    public void demo() throws Exception {
        System.out.println(name());
        Thread t = new Thread(()->{
            while (running) {
                System.out.println(running);
            }
        });
        Thread stop = new Thread(()->{
            stopThread();
            System.out.println("Thread has been stoped not running var now is: "+running);
        },"off/button");
        t.start();
        stop.start();
        t.join();
        stop.join();
    }
}

