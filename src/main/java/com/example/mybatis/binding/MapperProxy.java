package com.example.mybatis.binding;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;


public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;

    private Map<String, String> sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(Map<String, String> sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 接口创建出的代理对象不仅有实现接口的方法,也有从Object继承过来的方法,如果利用动态代理对象调用的是toString，hashCode,getClass等这些从Object类继承过来的方法，就直接反射调用。如果调用的是接口规定的方法。我们就用MapperMethod来执行。
        // method.getDeclaringClass用来判断当前这个方法是哪个类的方法。
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            return "你的被代理了！" + sqlSession.get(mapperInterface.getName() + "." + method.getName());
        }
    }

}
