package top.elichn.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * <p>Title: InvocationHandlerClient</p>
 * <p>Description: InvocationHandlerClient</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2019/12/28
 */
public class InvocationHandlerClient {

    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        InvocationHandlerImpl invokeHandler = new InvocationHandlerImpl(realSubject);
        // 1.获取对应的ClassLoader
        ClassLoader classLoader = realSubject.getClass().getClassLoader();
        // 2.获取realSubject 所实现的所有接口
        Class[] interfaces = realSubject.getClass().getInterfaces();
		/*创建代理对象 在这个过程中，
		  a.JDK会通过根据传入的参数信息动态地在内存中创建和.class 文件等同的字节码
		  b.然后根据相应的字节码转换成对应的class，
          c.然后调用newInstance()创建实例*/
        Object object = Proxy.newProxyInstance(classLoader, interfaces, invokeHandler);
        // subject是Proxy的一个实例
        System.out.println(object instanceof Proxy);
        // subject的Class类是$Proxy0,这个$Proxy0类继承了Proxy，实现了Subject接口
        System.out.println("subject的Class类是：" + object.getClass());

        Subject subject = (Subject) object;
        // 调用$Proxy0类中的request()方法，进而调用父类Proxy中的h的invoke()方法.即InvocationHandler.invoke()
        subject.request();

    }
}
