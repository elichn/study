package top.elichn.thread.productconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Title: ProductorConsumer</p>
 * <p>Description: </p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/1/1
 */
public class ProductorConsumer {

    private static Lock lock = new ReentrantLock();
    private static int totalNum = 10;
    private static int count = 0;
    private static boolean isProducted = false;

    private static Condition productorConditon = lock.newCondition();
    private static Condition consumerConditon = lock.newCondition();

    public static void main(String[] args) {
        int totalThreadSize = 5;
        int productorThreadSize = 2;
        int consumerThreadSize = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(totalThreadSize);
        for (int i = 0; i < productorThreadSize; i++) {
            executorService.submit(new Thread(new Producer()));
        }
        for (int i = 0; i < consumerThreadSize; i++) {
            // System.out.println(i);
            executorService.submit(new Thread(new Consumer()));
        }
        executorService.shutdown();
    }

    /**
     * 生产者线程
     */
    static class Producer extends Thread {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    // 使用线程的等待/通知机制时，一般都要配合一个 boolean 变量值
                    // 一般都要在 wait 方法外围加一层 while 循环，以防止早期通知(调用wait会一直阻塞)
                    // while 循环的另一个作用防止 wait条件发生变化  满足 while 循环的条件时，进入 while 循环，执行 wait()方法，不满足 while 循环的条件时，跳出循环，执行后面的代码
                    while (isProducted) {
                        try {
                            productorConditon.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 只定义生产多少只鸭子
                    if (count < totalNum) {
                        count++;
                        System.out.println(Thread.currentThread().getName() + " 生产.." + count);
                        isProducted = true;
                        // 唤醒消费线程
                        consumerConditon.signal();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * 消费者线程
     */
    static class Consumer extends Thread {

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (!isProducted) {
                        try {
                            consumerConditon.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " 消费.." + count);
                    isProducted = false;
                    // 唤醒生产线程
                    productorConditon.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
