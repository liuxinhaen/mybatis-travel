package com.example.mybatis.binding;

import com.example.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * 用来生产Mapper的代理对象的工厂
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    //@SuppressWarnings注解告诉编译器忽略指定的警告，不在编译完成后出现警告信息，这些警告信息显示在代码左侧行列栏，会挡住调试时的断点。可注解类、字段、方法、参数、局部变量、构造函数。unchecked：抑制没有进行类型检查操作的警告
    @SuppressWarnings("unchecked")
    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        /** newProxyInstance 有三个参数：
         *  1.loader :用哪个类加载器去加载代理对象
         *  2.interface：动态代理类需要实现的接口
         *  3.InvocationHandler类型参数h,动态代理方法在执行时，会调用h里面的invoke方法去执行 下面的invoke方法就是com.example.mybatis.binding.MapperProxy#invoke
         */
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }

}
