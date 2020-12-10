package top.elichn.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>Title: InvocationHandlerImpl</p>
 * <p>Description: InvocationHandlerImpl</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2019/12/28
 */
public class InvocationHandlerImpl implements InvocationHandler {

    private Subject subject;

    public InvocationHandlerImpl(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before calling " + method.getName() + ".");
        method.invoke(subject, args);
        System.out.println("after calling " + method.getName() + ".");

        ProxyUtils.generateClassFile(subject.getClass(), "SubjectProxy");
        return null;
    }
}
