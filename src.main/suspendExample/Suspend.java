package suspendExample;

class TestThread implements Runnable {

    String name;
    Thread thread;
    boolean suspendFlag;

    public TestThread(String name) {
        this.name = name;
        thread = new Thread(this, name);
        System.out.println("New thread: " + thread);
        suspendFlag = false;
        thread.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 15; i++) {
                System.out.println(name + ": " + i);
                Thread.sleep(200);
                synchronized (this) {
                    while (suspendFlag) {
                        wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted");
        }
        System.out.println(name + " completed");
    }

    synchronized void suspend() {
        suspendFlag = true;

    }
    synchronized void resume() {
        suspendFlag = false;
        notify();
    }
}

class Suspend {
    public static void main(String[] args) {
        TestThread threadOne = new TestThread("One");
        TestThread threadTwo = new TestThread("Two");

        try {
            Thread.sleep(1000);
            threadOne.suspend();
            System.out.println("One suspended");
            Thread.sleep(1000);
            threadOne.resume();
            System.out.println("One resume");
            threadTwo.suspend();
            System.out.println("Two suspended");
            Thread.sleep(1000);
            threadTwo.resume();
            System.out.println("Two resume");
        } catch (InterruptedException e) {
            System.out.println("Main interrupted");
        }

        try {
            System.out.println("Waiting for the end of threads");
            threadOne.thread.join();
            threadTwo.thread.join();
        } catch (InterruptedException e) {
            System.out.println("Main interrupted");
        }
        System.out.println("Main completed");


    }
}
