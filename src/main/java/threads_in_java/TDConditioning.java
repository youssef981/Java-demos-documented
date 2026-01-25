package threads_in_java;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TDConditioning implements ThreadsDemo{

    private final String NAME = "\nConditioning in threads";
    CountDownLatch latch = new CountDownLatch(2);


    @Override
    public String name() {
        return NAME;
    }
    private Lock lock = new ReentrantLock() ;
    private Condition condition = lock.newCondition();
    private boolean ready = false;

    public void produce() throws InterruptedException {
        lock.lock();
        try {
            while (ready) {
                condition.await();
            }
            //
            System.out.println("Producing data...");
            ready = true;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (!ready) {
                condition.await();
            }
            System.out.println("Consuming data...");
            ready = false;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void execute() throws Exception {
        System.out.println(name());
        Thread producer = new Thread(() -> {
            try {
                produce();
            } catch (InterruptedException e) {}
            latch.countDown();
        });
        Thread consumer = new Thread(() -> {
            try {
                consume();
            } catch (InterruptedException e) {}
            latch.countDown();
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
