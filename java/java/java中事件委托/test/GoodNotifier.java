package com.test;

import java.lang.reflect.InvocationTargetException;

public class GoodNotifier extends Notifier {
    @Override
    public void addListener(Object object, String methodName, Object... args) {
        System.out.println("收到事件委托");
        this.getHandler().addEvent(object,methodName,args);
    }

    @Override
    public void notifyX() {
        try {
            this.getHandler().Notify();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
