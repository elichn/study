package top.elichn.thread.productconsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>Title: ProductorConsumer4Share</p>
 * <p>Description: ProductorConsumer4Share</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/1/2
 */
public class ProductorConsumer4Share {

    private static Lock lock = new ReentrantLock();
    private static Condition productorConditon = lock.newCondition();
    private static Condition consumerConditon = lock.newCondition();

    private static int totalNum = 50;
    private static int count = 0;

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        int totalThreadSize = 100;
        int productorThreadSize = 1;
        int consumerThreadSize = 99;

        ExecutorService executorService = Executors.newFixedThreadPool(totalThreadSize);

        for (int i = 0; i < productorThreadSize; i++) {
            executorService.submit(new Productor(linkedList, lock, totalNum));
        }

        for (int i = 0; i < consumerThreadSize; i++) {
            executorService.submit(new Consumer(linkedList, lock));
        }
        executorService.shutdown();
    }

    /**
     * 生产者线程
     */
    static class Productor implements Runnable {
        private List<Integer> list;
        private Lock lock;
        private int totalNum;

        public Productor(List<Integer> list, Lock lock, int totalNum) {
            this.list = list;
            this.lock = lock;
            this.totalNum = totalNum;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (list.size() == totalNum) {
                        try {
                            productorConditon.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 只定义生产多少只鸭子
                    if (count < totalNum) {
                        count++;
                        list.add(count);

                        System.out.println(Thread.currentThread().getName() + " 生产.." + count);
                        // 唤醒消费线程
                        consumerConditon.signal();
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
    static class Consumer implements Runnable {
        private List<Integer> list;
        private Lock lock;

        public Consumer(List<Integer> list, Lock lock) {
            this.list = list;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (list.isEmpty()) {
                        try {
                            consumerConditon.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Integer consumeInt = list.remove(0);
                    System.out.println(Thread.currentThread().getName() + " 消费.." + consumeInt);
                    // 唤醒生产线程
                    productorConditon.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
