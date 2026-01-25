package threads_in_java;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class DemoRunner {

    public static void main(String[] args) {

        List<DemoEntry> demoEntries = List.of(
                new DemoEntry(new TDConditioning(), true),
                new DemoEntry(new TDCallables(), false),
                new DemoEntry(new TDLocalThread(), true),
                new DemoEntry(new TDSynchronizedWaitNotif(), false),
                new DemoEntry(new TDFutures(), false),
                new DemoEntry(new TDVolatile(), true)
        );

        demoEntries.forEach(entry -> {
            if (entry.enabled()) {
                entry.demo().on();
            } else {
                entry.demo().off();
            }
        });

        demoEntries.stream()
                .filter(DemoEntry::enabled)
                .map(DemoEntry::demo)
                .forEach(demo -> {
                    try {
                        demo.execute();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

        AtomicInteger i = new AtomicInteger(0);

//        Future<?> f =  poolSheduled.scheduleAtFixedRate(
//                ()-> {
//                    int v = i.getAndIncrement();
//                    System.out.println(i+" "+Thread.currentThread().getName());
//                }
//                ,1,1,TimeUnit.SECONDS);

//        Future<?> f =  poolSheduled.scheduleWithFixedDelay(
//                ()-> {
//                    System.out.println("task picked after the fixed dely of 2s");
//                    int v = i.getAndIncrement();
//                    System.out.println(i+" "+Thread.currentThread().getName());
//                }
//                ,7,2,TimeUnit.SECONDS);
//
//        System.out.println("main Thread is completed");
//        try {
//            System.out.println("main thread is here");
//            Thread.sleep(150000);
//            System.out.println(Thread.currentThread().getName() + ": canceled the scheduled one");
//            f.cancel(true);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }


//        try {
//            System.out.println(future.get());
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        }

//        poolObj.shutdownNow();
//        try {
//            poolObj.awaitTermination(4, TimeUnit.SECONDS);
//            System.out.println("termination is comleted");
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//        poolObj.submit(() -> {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println("Task is completed");
//        });


//    public void run(){
//        for(int i = 0;i<=5;i++){
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            System.out.println(Thread.currentThread().getName());
//        }

    }

