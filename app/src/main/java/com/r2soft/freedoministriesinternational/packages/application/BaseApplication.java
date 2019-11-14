package com.r2soft.freedoministriesinternational.packages.application;

import android.app.Application;

public class BaseApplication extends Application {

    private static BaseApplication inst =null;


    public BaseApplication(){
        inst = this;
    }

    public static BaseApplication getInstance(){
        return inst;
    }

}
