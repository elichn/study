package top.elichn.thread.alternatelyprint;

import java.util.concurrent.CountDownLatch;

/**
 * <p>Title: AlternatelyPrintChar4NotifyLatch</p>
 * <p>Description: AlternatelyPrintChar4NotifyLatch</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2019/12/15
 */
public class AlternatelyPrintChar4NotifyLatch {

    private static Object object = new Object();
    private static char[] char1 = "123".toCharArray();
    private static char[] char2 = "abc".toCharArray();

    // 优雅解法，先打印a
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (object) {
                for (char c : char1) {
                    System.out.println(c);
                    try {
                        object.notify();
                        // 让出锁
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (object) {
                for (char c : char2) {
                    System.out.println(c);
                    latch.countDown();
                    try {
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        }, "t2").start();
    }

}
