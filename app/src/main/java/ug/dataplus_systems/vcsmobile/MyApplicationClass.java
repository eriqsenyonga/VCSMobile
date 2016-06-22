package ug.dataplus_systems.vcsmobile;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Eriq on 1/27/2016.
 */
public class MyApplicationClass extends Application {

    private RequestQueue mRequestQueue;
    private static MyApplicationClass mInstance;
    public static final String TAG = MyApplicationClass.class.getName();
    private String generalUrl = "http://172.20.10.9/";


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static synchronized MyApplicationClass getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public <T> void add(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancel() {

        mRequestQueue.cancelAll(TAG);
    }

    public String getGeneralUrl() {
        return generalUrl;
    }
}
