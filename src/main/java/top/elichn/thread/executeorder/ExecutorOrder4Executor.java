package top.elichn.thread.executeorder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>Title: ExecutorOrder4Executor</p>
 * <p>Description: ExecutorOrder4Executor</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2019/12/17
 */
public class ExecutorOrder4Executor {

    private static Thread thread1 = new Thread(() -> {
        System.out.println("thread1");
    });


    private static Thread thread2 = new Thread(() -> {
        System.out.println("thread2");
    });


    private static Thread thread3 = new Thread(() -> {
        System.out.println("thread3");
    });

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        executorService.submit(thread1);
        executorService.submit(thread2);
        executorService.submit(thread3);
        executorService.shutdown();
    }
}
