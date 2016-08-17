package model;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.matschema.my.matschema.MyApp;

/**
 * Created by my on 2015-02-20.
 *
 *
 */


public class VollySingleton {

    private static VollySingleton sInstance=null;
    protected RequestQueue mRequestQueue;


    private VollySingleton(){

        mRequestQueue=Volley.newRequestQueue(MyApp.getAppContext());

    }

    public static VollySingleton getsInstance(){

        if(sInstance==null){
            sInstance=new VollySingleton();


        }

        return sInstance;


    }

    public RequestQueue getmRequestQueue(){

        return mRequestQueue;


    }
}
