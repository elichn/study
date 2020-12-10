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
public class AlternatelyPrintChar4Condition2Latch {

    private static char[] char1 = "123".toCharArray();
    private static char[] char2 = "abc".toCharArray();
    private static char[] char3 = "xyz".toCharArray();

    // 优雅解法，先打印a
    private static CountDownLatch latch = new CountDownLatch(1);

    private static Lock lock = new ReentrantLock();
    private static Condition conditionT1 = lock.newCondition();
    private static Condition conditionT2 = lock.newCondition();
    private static Condition conditionT3 = lock.newCondition();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                latch.await();
                lock.lock();
                for (char c : char1) {
                    System.out.println(c);
                    conditionT2.signal();
                    conditionT3.signal();
                    conditionT1.await();
                }
//                conditionT2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : char2) {
                    System.out.println(c);
                    latch.countDown();
                    conditionT1.signal();
                    conditionT3.signal();
                    // 让出锁
                    conditionT2.await();
                }
//                conditionT1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();


        new Thread(() -> {
            try {
                lock.lock();
                for (char c : char3) {
                    System.out.println(c);
                    latch.countDown();
                    conditionT1.signal();
                    // 让出锁
                    conditionT2.signal();
                    conditionT3.await();
                }
//                conditionT1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t3").start();
    }

}
