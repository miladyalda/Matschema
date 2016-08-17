package com.matschema.my.matschema;

import android.app.Application;
import android.content.Context;

/**
 * Created by my on 2015-02-20.
 */
public class MyApp extends Application {

    private static MyApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
    }

    public static MyApp getsInstance(){


        return sInstance;


    }

    public static Context getAppContext(){

        return  sInstance.getApplicationContext();



    }
}
