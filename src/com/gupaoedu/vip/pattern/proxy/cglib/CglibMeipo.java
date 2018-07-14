package com.gupaoedu.vip.pattern.proxy.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Tom on 2018/3/10.
 */
public class CglibMeipo implements MethodInterceptor{

    private Object target = null;

    public Object getProxy(Object target)
    {
        Enhancer enhancer = new Enhancer();
        //要把哪个设置为即将生成的新类父类
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        this.target = target;
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //业务的增强
        if(method.getDeclaringClass() == target.getClass()) {
            System.out.println("我是媒婆：我要给你找对象，现在已经拿到你的需求");
            System.out.println("开始物色");

            // methodProxy.invokeSuper(target, objects);
            method.invoke(target, objects);

            System.out.println("如果合适的话，就准备办事");
        }
        else
        {
            return methodProxy.invokeSuper(o, objects);
        }
        return null;
    }
}
