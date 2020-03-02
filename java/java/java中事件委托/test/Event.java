package com.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Event {

    private String methodName;
    private Object object;
    private Object[] params;
    private Class[] paramTypes;

    public Event(Object object,String methodName,Object[] params){
        this.object=object;
        this.methodName=methodName;
        this.params=params;
        this.paramTypes=transParamTypes(params);
    }

    public Class[] transParamTypes(Object[] params){
        Class[] clazz=new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            Class<?> aClass = params[i].getClass();
            clazz[i]=aClass;
        }
        return clazz;
    }

    public void invoke() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = object.getClass().getMethod(methodName, paramTypes);
        if(method==null){
            return;
        }
        method.invoke(object,params);
    }
}
