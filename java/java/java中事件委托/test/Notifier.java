package com.test;

public abstract class Notifier {
    private EventHandler handler=new EventHandler();

    public EventHandler getHandler() {
        return handler;
    }

    public abstract  void addListener(Object object,String methodName,Object...args);
    public abstract void notifyX();
}
