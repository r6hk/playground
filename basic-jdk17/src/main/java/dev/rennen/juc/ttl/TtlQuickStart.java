package dev.rennen.juc.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <br/>
 * 2025/4/2
 *
 * @author rennen.dev
 */
public class TtlQuickStart {

    static TransmittableThreadLocal<Integer> ttlInteger = new TransmittableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        ttlInteger.set(1);
        Runnable task = () -> {
            System.out.println("ThreadLocal value: " + ttlInteger.get());
        };
        Thread thread = new Thread(task);
        thread.start();
        Thread.sleep(1000);
        ttlInteger.remove();

    }
}
