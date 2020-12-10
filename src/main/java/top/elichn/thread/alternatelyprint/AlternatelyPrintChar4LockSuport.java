package top.elichn.thread.alternatelyprint;

import java.util.concurrent.locks.LockSupport;

/**
 * <p>Title: AlternatelyPrintChar4LockSuport</p>
 * <p>Description: AlternatelyPrintChar4LockSuport</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2019/12/15
 */
public class AlternatelyPrintChar4LockSuport {

    private static char[] char1 = "123".toCharArray();
    private static char[] char2 = "abc".toCharArray();

    private static Thread t1 = null;
    private static Thread t2 = null;

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            for (char c : char1) {
                System.out.println(c);
                // 让t2继续运行
                LockSupport.unpark(t2);
                // 让当前线程t1阻塞（暂停）
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : char2) {
                // 让当前线程t2阻塞（暂停），先让t1输出
                LockSupport.park();
                // 有线程叫醒t2，就输出
                System.out.println(c);
                // 让t2继续运行
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
