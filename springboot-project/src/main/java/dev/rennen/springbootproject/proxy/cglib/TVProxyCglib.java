package dev.rennen.springbootproject.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TVProxyCglib implements MethodInterceptor {

    //给目标对象创建一个代理对象
    public Object getProxyInstance(Class c){
        //1.工具类
        Enhancer enhancer = new Enhancer();
        //2.设置父类
        enhancer.setSuperclass(c);
        //3.设置回调函数
        enhancer.setCallback(this);
        //4.创建子类（代理对象）
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("TVProxyFactory enhancement.....");
        return methodProxy.invokeSuper(o, objects);
    }
}