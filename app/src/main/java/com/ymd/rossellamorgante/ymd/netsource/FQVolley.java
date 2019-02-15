package com.ymd.rossellamorgante.ymd.netsource;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ymd.rossellamorgante.ymd.model.FSPlace;
import com.ymd.rossellamorgante.ymd.util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FQVolley  {
    Context _c;
    RequestQueue queue;

    public FQVolley(Context context){
        _c=context;
        queue = Volley.newRequestQueue(_c);
    }

    public void makeSearchUrl(String near,Response.Listener<JSONObject> onResponse) throws IOException{

        String url = Utils.getProperty("fs_url",_c)+"?"+
                "client_id="+Utils.getProperty("client_id",_c)+"&"+
                "client_secret="+Utils.getProperty("client_secret",_c)+"&"+
                "v="+Utils.getProperty("v",_c)+"&"+
                "near="+near;

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                onResponse,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        queue.add(getRequest);
    }

}





