package com.example.mybatis.binding;

import java.lang.reflect.Proxy;
import java.util.Map;


public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Map<String, String> sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        /** newProxyInstance 有三个参数：
         *  1.loader :用哪个类加载器去加载代理对象
         *  2.interface：动态代理类需要实现的接口
         *  3.InvocationHandler类型参数h,动态代理方法在执行时，会调用h里面的invoke方法去执行 下面的invoke方法就是com.example.mybatis.binding.MapperProxy#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
         */
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }

}
