package dev.rennen.juc.ttl;

public class InheritableThreadLocalTest {

    private static final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        inheritableThreadLocal.set("父线程的值");

        Thread childThread = new Thread(() -> {
            String value = inheritableThreadLocal.get();
            System.out.println("子线程获取到的值: " + value);
        });

        inheritableThreadLocal.remove();
        inheritableThreadLocal.set("父线程的值2");

        childThread.start();
        childThread.join(); // 等待子线程结束
    }
}
