package com.example.ruelas.yuda;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ruelas on 20/02/2017.
 */
public class VolleySing {
    private static VolleySing instance;
    private RequestQueue rq;

    private VolleySing(Context context){
        this.rq= Volley.newRequestQueue(context.getApplicationContext());

    }
    public static VolleySing getInstance(Context context){
        if(instance!=null)
            return instance;
        else
            return new VolleySing(context);
    }
    public void addStringRequest(StringRequest request){
        rq.add(request);
    }
    public void stopRequests(){
        rq.stop();
    }
}
