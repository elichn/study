package top.elichn.thread.alternatelyprint;

/**
 * <p>Title: AlternatelyPrintChar4NotifyLatch</p>
 * <p>Description: AlternatelyPrintChar4NotifyLatch</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2019/12/15
 */
public class AlternatelyPrintChar4NotifyWhile {

    private static Object object = new Object();
    private static char[] char1 = "123".toCharArray();
    private static char[] char2 = "abc".toCharArray();

    // 优雅解法，先打印a
    private static volatile Boolean isThread2Start = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (object) {
                for (char c : char1) {
                    while (!isThread2Start) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.println(c);
                    isThread2Start = false;
                    object.notify();
                }
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (object) {
                for (char c : char2) {
                    while (isThread2Start) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(c);
                    isThread2Start = true;
                    object.notify();
                }
            }
        }, "t2").start();
    }

}
