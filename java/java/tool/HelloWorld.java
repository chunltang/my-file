// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HelloWorld.java

import test.Server;

public class HelloWorld
{

    public HelloWorld()
    {
    }

    public static void main(String args[])
    {
        HelloWorld helloWord = new HelloWorld();
        helloWord.sayHello("nihao");
        new Server(8080);
    }

    public native void sayHello(String s);

    static 
    {
        System.load("D:\\java\\jni\\HelloNative.dll");
    }
}
