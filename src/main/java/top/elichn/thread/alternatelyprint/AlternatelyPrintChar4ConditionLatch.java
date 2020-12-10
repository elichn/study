package top.elichn.thread.alternatelyprint;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Title: AlternatelyPrintChar4NotifyLatch</p>
 * <p>Description: AlternatelyPrintChar4NotifyLatch</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2019/12/15
 */
public class AlternatelyPrintChar4ConditionLatch {

    private static char[] char1 = "123".toCharArray();
    private static char[] char2 = "abc".toCharArray();

    // 优雅解法，先打印a
    private static CountDownLatch latch = new CountDownLatch(1);

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                latch.await();
                lock.lock();
                for (char c : char1) {
                    System.out.println(c);
                    condition.signal();
                    // 让出锁
                    condition.await();
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                for (char c : char2) {
                    System.out.println(c);
                    latch.countDown();
                    condition.signal();
                    // 让出锁
                    condition.await();
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }

}
