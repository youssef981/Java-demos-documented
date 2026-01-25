package tests;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyInvocationHandler implements InvocationHandler {

    private static final Logger log =
            LoggerFactory.getLogger(MyInvocationHandler.class);

    private final Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    public static Object newInstance(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                ClassUtils.getAllInterfaces(target.getClass()).toArray(new Class[0]),
                new MyInvocationHandler(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

        log.info("invoke calling: {}({})", method.getName(), args);

        Object result = method.invoke(target, args);

        log.info("invoke {} returned: {}", method.getName(), result);
        return result;
    }
}
