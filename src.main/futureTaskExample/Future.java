package futureTaskExample;


import java.util.concurrent.*;

class CallableClass implements Callable<String> {

    private final long timer;

    public CallableClass(long timer) {
        this.timer = timer;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(timer);
        return Thread.currentThread().getName();
    }
}

public class Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CallableClass callableClass1 = new CallableClass(1000);
        CallableClass callableClass2 = new CallableClass(2000);

        FutureTask<String> futureTask1 = new FutureTask<>(callableClass1);
        FutureTask<String> futureTask2 = new FutureTask<>(callableClass2);

        ExecutorService es = Executors.newFixedThreadPool(2);

        es.execute(futureTask1);
        es.execute(futureTask2);

        while (true) {
            if (futureTask1.isDone() && futureTask2.isDone()) {
                System.out.println("Done");
                es.shutdown();
                System.out.println("Executor is down");
                return;
            }
            if (!futureTask1.isDone()) {
                System.out.println("FutureTask1 output " + futureTask1.get());
            }
            System.out.println("Wait for FutureTask2" );
            String str = futureTask2.get(1000, TimeUnit.MILLISECONDS);
//            String str = futureTask2.get(200, TimeUnit.MILLISECONDS); TimerException
            if (str != null) {
                System.out.println("FutureTask2 output " + futureTask2.get());
            }
        }
    }
}
