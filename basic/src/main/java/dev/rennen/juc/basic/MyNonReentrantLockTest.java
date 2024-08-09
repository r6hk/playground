package dev.rennen.juc.basic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;

/**
 * @author rennen.dev
 * @date 2024/8/9 19:03
 */
public class MyNonReentrantLockTest implements Lock {


    private static class Sync extends AbstractQueuedSynchronizer {

        /**
         * 锁是否被线程持有
         */
        @Override
        protected boolean isHeldExclusively() {
            //0：未持有 1：已持有
            return super.getState() == 1;
        }

        /**
         * 获取锁
         */
        @Override
        protected boolean tryAcquire(int arg) {
            if (arg != 1) {
                //获取锁操作，是需要把state更新为1，所以arg必须是1
                throw new RuntimeException("arg not is 1");
            }
            if (compareAndSetState(0, arg)) {//cas 更新state为1成功，代表获取锁成功
                //设置持有锁线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁
         */
        @Override
        protected boolean tryRelease(int arg) {
            if (arg != 0) {
                //释放锁操作，是需要把state更新为0，所以arg必须是0
                throw new RuntimeException("arg not is 0");
            }
            //清空持有锁线程
            setExclusiveOwnerThread(null);
            //设置state状态为0，此处不用cas，因为只有获取锁成功的线程才会执行该函数，不需要考虑线程安全问题
            setState(arg);
            return true;
        }

        /**
         * 提供创建条件变量入口
         */
        public ConditionObject createConditionObject() {
            return new ConditionObject();
        }

    }

    private final Sync sync = new Sync();

    /**
     * 获取锁
     */
    @Override
    public void lock() {
        //Aqs独占式-获取资源模板函数
        sync.acquire(1);
    }

    /**
     * 获取锁-响应中断
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        //Aqs独占式-获取资源模板函数(响应线程中断)
        sync.acquireInterruptibly(1);
    }

    /**
     * 获取锁是否成功-不阻塞
     */
    @Override
    public boolean tryLock() {
        //子类实现
        return sync.tryAcquire(1);
    }

    /**
     * 获取锁-超时机制
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        //Aqs独占式-获取资源模板函数(超时机制)
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        //Aqs独占式-释放资源模板函数
        sync.release(0);
    }

    /**
     * 创建条件变量
     */
    @Override
    public AbstractQueuedSynchronizer.ConditionObject newCondition() {
        return sync.createConditionObject();
    }

    public static void main(String[] args) throws InterruptedException {
        MyNonReentrantLockTest testLock = new MyNonReentrantLockTest();
        Runnable r = () -> {
            for (int i = 0; i < 10; i++) {
                if (i == 5) {
                    testLock.tryLock();
                }
                System.out.println("Thread Print:" + i);
            }
            testLock.unlock();
        };
        Thread t = new Thread(r);
        Thread t2 = new Thread(r);
        t.start();
        Thread.sleep(3000);
        testLock.unlock();
    }
}