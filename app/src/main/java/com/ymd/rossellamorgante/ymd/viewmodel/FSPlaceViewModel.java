package com.ymd.rossellamorgante.ymd.viewmodel;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ymd.rossellamorgante.ymd.model.FSPlace;
import com.ymd.rossellamorgante.ymd.netsource.FQVolley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FSPlaceViewModel extends ViewModel {

    private MutableLiveData<ArrayList<FSPlace>> venues;
    private Context c;

    public MutableLiveData<ArrayList<FSPlace>> getVenues(Context _c) {
        c=_c;
        if (venues == null) {
            venues = new MutableLiveData<ArrayList<FSPlace>>();
        }
        return venues;
    }


    public void search(String near) {

        FQVolley fq= new FQVolley(c);
        try {
            fq.makeSearchUrl(near,onResponse,onError);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Response.Listener<JSONObject> for Venues
     Response.Listener<JSONObject> onResponse = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                Gson gson = new GsonBuilder().create();
                JSONArray vns = (response.getJSONObject("response")).getJSONArray("venues");
                FSPlace[] p = gson.fromJson( vns.toString() , FSPlace[].class);
                ArrayList<FSPlace> list = new ArrayList( Arrays.asList(p));
                venues.setValue(list);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    Response.ErrorListener onError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            venues.setValue(null);
        }
    };
}
