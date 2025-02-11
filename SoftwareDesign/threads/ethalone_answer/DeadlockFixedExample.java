package threads;

public class DeadlockFixedExample {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 acquired lock1");

                try { Thread.sleep(50); } catch (InterruptedException e) {}

                synchronized (lock2) {
                    System.out.println("Thread 1 acquired lock2");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 2 acquired lock1");

                try { Thread.sleep(50); } catch (InterruptedException e) {}

                synchronized (lock2) {
                    System.out.println("Thread 2 acquired lock2");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}