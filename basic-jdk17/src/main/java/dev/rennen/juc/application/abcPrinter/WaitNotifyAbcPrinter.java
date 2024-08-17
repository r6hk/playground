package dev.rennen.juc.application.abcPrinter;

/**
 * @author rennen.dev
 * @date 2024/8/7 19:15
 */
public class WaitNotifyAbcPrinter {

    public void print(String str, int waitFlag, int nextFlag) {
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this) {
                while(flag != waitFlag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }

    // 等待标记
    private int flag; // 2
    // 循环次数
    private final int loopNumber;

    public WaitNotifyAbcPrinter(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }

    public static void main(String[] args) {
        WaitNotifyAbcPrinter wn = new WaitNotifyAbcPrinter(1, 5);
        new Thread(() -> {
            wn.print("a", 1, 2);
        }).start();
        new Thread(() -> {
            wn.print("b", 2, 3);
        }).start();
        new Thread(() -> {
            wn.print("c", 3, 1);
        }).start();
    }

}
