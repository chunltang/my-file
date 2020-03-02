package com.test;

import java.util.Date;

public class WatchingTVListener {

    public WatchingTVListener(){
        System.out.println("我正在玩游戏，开始时间："+new Date());
    }

    public void stopWatchingTV(Date date){
        System.out.println("老师来了,快关闭电视，结束时间："+date);
    }
}
