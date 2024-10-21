package dev.rennen;

/**
 * @author rennen.dev
 * @date 2024/10/7 15:15
 */
public class DeadLockTest {

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("t1-获取到锁 1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    System.out.println("获取到锁 2");
                }
            }

        });

        Thread t2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("t2- 获取到锁 2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock1) {
                    System.out.println("获取到锁 1");
                }
            }

        });

        t1.start();
        t2.start();

        Thread.sleep(5000);

        public static boolean isCircle(String s) {

            int left = 0, right = s.length() - 1;

            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
    }
}
