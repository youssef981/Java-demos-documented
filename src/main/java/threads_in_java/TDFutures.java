package threads_in_java;

import java.util.concurrent.*;

public class TDFutures implements ThreadsDemo{

    private final String NAME = "\nFutures Demo";
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
    CountDownLatch latch = new CountDownLatch(2); // one for demo title and the other fo the task itself

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void demo() throws Exception {
        System.out.println(name());
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Callable<String> callableTask = () -> {
            try {
                Thread.sleep(2000); // Simulate a long-running task
                return "Result from Callable";
            } finally {
                latch.countDown();
            }
        };

        Runnable submitionConfirmation = new Runnable() {
            @Override
            public void run() {
                System.out.println("Task submitted. Waiting for result...");
                latch.countDown();
            }
        };

        // Submit the Callable task to the ExecutorService
        Future<String> future = executor.submit(callableTask);
        executor.submit(submitionConfirmation);
        try {
            // Get the result from the Future (blocking call)
            String result = future.get();
            System.out.println("Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // Shutdown the ExecutorService
        }
    }
}
