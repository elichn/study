package top.elichn.thread.productconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Title: ProductorConsumer4Queue</p>
 * <p>Description: ProductorConsumer4Queue</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2020/1/3
 */
public class ProductorConsumer4Queue {

    private static int totalNum = 10;
    private static volatile AtomicInteger count = new AtomicInteger(1);

    private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        int totalThreadSize = 5;
        int productorThreadSize = 2;
        int consumerThreadSize = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(totalThreadSize);
        for (int i = 0; i < productorThreadSize; i++) {
            executorService.submit(new Productor(queue));
        }
        for (int i = 0; i < consumerThreadSize; i++) {
            executorService.submit(new Consumer(queue));
        }
        executorService.shutdown();
    }

    /**
     * 生产者线程
     */
    static class Productor implements Runnable {
        private BlockingQueue<Integer> queue;

        public Productor(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                int productInt = count.getAndIncrement();
                // 只定义生产多少只鸭子
                if (productInt <= totalNum) {
                    try {
                        queue.put(productInt);
                        System.out.println(Thread.currentThread().getName() + " 生产.." + productInt);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    /**
     * 消费者线程
     */
    static class Consumer implements Runnable {
        private BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Integer consumeInt = queue.take();
                    System.out.println(Thread.currentThread().getName() + " 消费.." + consumeInt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
