package top.elichn.thread.executeorder;

/**
 * <p>Title: ExecutorOrder4Join</p>
 * <p>Description: ExecutorOrder4Join</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2019/12/17
 */
public class ExecutorOrder4Join {

    private static Thread thread1 = new Thread(() -> {
        System.out.println("thread1");
    });


    private static Thread thread2 = new Thread(() -> {
        System.out.println("thread2");
    });


    private static Thread thread3 = new Thread(() -> {
        System.out.println("thread3");
    });

    public static void main(String[] args) throws InterruptedException {
        // start 不是立即执行，等待cpu调度
        thread1.start();
        // join()保证线程顺序执行 本身调用object.wait()方法，让主线程等待
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
    }


}
