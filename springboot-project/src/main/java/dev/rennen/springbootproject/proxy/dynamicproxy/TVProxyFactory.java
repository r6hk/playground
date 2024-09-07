package dev.rennen.springbootproject.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TVProxyFactory {

    private final Object target;

    public TVProxyFactory(Object o){
        this.target = o;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("TV proxy find factory for tv.... ");
                        return method.invoke(target, args);
                    }
                });
    }
}