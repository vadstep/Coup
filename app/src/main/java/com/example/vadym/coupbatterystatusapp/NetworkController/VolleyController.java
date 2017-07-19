package com.example.vadym.coupbatterystatusapp.NetworkController;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vadym.coupbatterystatusapp.Utils.AppConst;
import com.example.vadym.coupbatterystatusapp.Utils.PreferenceUtils;

/**
 * this class provides network connection service
 */
public class VolleyController {
    public static final String TAG = VolleyController.class.getSimpleName();
    private static VolleyController mInstance;
    private RequestQueue mRequestQueue;
    private static Context context;

    private VolleyController(Context context) {
        this.context = context;
        this.mRequestQueue = getRequestQueue();

    }

    /**
     * returns singleton instance of VolleyController
     * @param context
     * @return
     */
    public static synchronized VolleyController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyController(context.getApplicationContext());
        }
        return mInstance;
    }

    /**
     * returns singleton instance of RequestQueue
     * @return
     */
    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


    public void getBikes(final ResultListener resultListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConst.scootersURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null&& response instanceof String) {
                            resultListener.onSuccess(new String("getBikes->ok"));
                            PreferenceUtils.saveString(context,AppConst.SCOOTERS_PREF,response);
                        }else{
                            resultListener.onError(new Exception("getBikes->response == null||!String"));
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultListener.onError(error);
            }
        });
        addToRequestQueue(stringRequest);
    }
    public void getAreas(final ResultListener resultListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConst.areasURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null&& response instanceof String) {
                            resultListener.onSuccess(new String("getAreas->ok"));
                            PreferenceUtils.saveString(context,AppConst.AREAS_PREF,response);
                        }else{
                            resultListener.onError(new Exception("getAreas->response == null||!String"));
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultListener.onError(error);
            }
        });
        addToRequestQueue(stringRequest);
    }
}