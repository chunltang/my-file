package com.test;

import java.util.Date;

/**事件委托，使用java反射，将观察者和通知者完全解耦，观察者将事件交给委托调用，通知在完全不知道观察者的情况下实现不同观察者的不同方法的调用*/
public class EntrustTest {

    public static void main(String[] args) throws InterruptedException {
        Notifier notifier = new GoodNotifier();

        PlayingGameListener gameListener = new PlayingGameListener();

        WatchingTVListener tvListener = new WatchingTVListener();

        notifier.addListener(gameListener,"stopPlayGame",new Date());
        notifier.addListener(tvListener,"stopWatchingTV",new Date());

        Thread.sleep(2000);

        notifier.notifyX();
    }
}
