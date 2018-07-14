package com.gupaoedu.vip.pattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author whz
 * @create 2018-05-22 8:47
 * @desc ww
 **/
public class OutterProxy implements InvocationHandler {

    Object target;
    public Object getProxy(Object target)
    {
        this.target = target;

        Class<?> clazz = target.getClass();

        //下半截，老师深入底层来给大家讲解字节码是如何重组的
        //用来生成一个新的对象（字节码重组来实现）
        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass() == target.getClass().getInterfaces()[0]) {
            System.out.println("我是OutterProxy");

            method.invoke(target, args);

            System.out.println("OutterProxy处理结束");

            return null;
        }
        else
        {
            return method.invoke(target, args);
        }
    }
}