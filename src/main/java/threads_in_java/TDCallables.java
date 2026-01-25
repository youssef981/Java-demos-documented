package threads_in_java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TDCallables extends Thread implements ThreadsDemo, Callable<String> {

    private CountDownLatch latch = new CountDownLatch(6);
    private final String NAME = "\nCallables Demo";

    private int taskId;


    public TDCallables() {
    }

    public TDCallables(int taskId) {
        this.taskId = taskId;
    }
    public String call() throws Exception {
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "The task with id: "+taskId+" is completed";
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void execute() throws Exception {
        System.out.println(name());
        List<Future<String>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        for(int i=0;i<=6;i++){
            TDCallables myCallable = new TDCallables(i);
            try {
                Future<String> future = executor.submit((Callable<String>) myCallable);
                futures.add(future);
            }finally {
                latch.countDown();
            }
        }
        System.out.println("All callables are submitted...");
        System.out.println("Getting our futures from Callables...");
        for(Future<String> f:futures){
            System.out.println("Result: "+f.get());
        }
        executor.shutdown();
    }

}
