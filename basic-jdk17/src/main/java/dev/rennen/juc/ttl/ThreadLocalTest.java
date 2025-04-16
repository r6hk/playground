package dev.rennen.juc.ttl;

/**
 * <br/>
 * 2025/4/8
 *
 * @author rennen.dev
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println(thread.isDaemon());

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("over");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // t2.setDaemon(true);
        System.out.println(t2.isDaemon());
        t2.start();
    }
}
