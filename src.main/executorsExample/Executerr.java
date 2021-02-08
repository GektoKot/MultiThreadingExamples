package executorsExample;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executerr {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);

        es.execute(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("firstinpool1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        es.execute(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("firstinpool2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        es.execute(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("laterinpool");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
