package top.elichn.proxy.jdk;


/**
 * <p>Title: RealSubject</p>
 * <p>Description: RealSubject</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2019/12/28
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("RealSubject request...");
    }
}
