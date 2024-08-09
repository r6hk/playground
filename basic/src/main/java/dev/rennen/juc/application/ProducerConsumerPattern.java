package dev.rennen.juc.application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author rennen.dev
 * @date 2024/8/7 21:51
 */
public class ProducerConsumerPattern {

    // 改为 Map 实现也可
    static List<Pair<Integer, Boolean>> queue = new ArrayList<>();
    static Semaphore semaphore = new Semaphore(0);

    static class Pair<L, R> {
        L l;
        R r;

        public L getL() {
            return l;
        }

        public R getR() {
            return r;
        }

        public void setL(L l) {
            this.l = l;
        }

        public void setR(R r) {
            this.r = r;
        }

        public Pair(L l, R r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "l=" + l +
                    ", r=" + r +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 生产者线程
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("producer: " + i);
                queue.add(new Pair<>(i, false));
                semaphore.release();
            }
        }).start();

        // 消费者线程
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("consumer: " + i);
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                queue.get(i).setR(true);
            }
        }).start();

        Thread.sleep(1000);

        for (Pair<Integer, Boolean> integerBooleanPair : queue) {
            System.out.println(integerBooleanPair);
        }
    }
}
