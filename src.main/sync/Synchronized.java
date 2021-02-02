package sync;

class TestThread implements Runnable {

    private String name;
    private final Thread thread;
    private Caller caller;

    public TestThread(String name, Caller caller) {
        this.name = name;
        this.caller = caller;
        this.thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        caller.call(name);
    }

    public Thread getThread() {
        return thread;
    }
}

class Caller {
    synchronized void call(String name) { // Here is juice
        System.out.print("[" + name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("]");
    }
}

class Synchronized {
    public static void main(String[] args) {
        Caller caller = new Caller();
        TestThread tt1 = new TestThread("Uasya", caller);
        TestThread tt2 = new TestThread("Petya", caller);
        TestThread tt3 = new TestThread("Lusya", caller);


        try {
                tt1.getThread().join();
                tt2.getThread().join();
                tt3.getThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
