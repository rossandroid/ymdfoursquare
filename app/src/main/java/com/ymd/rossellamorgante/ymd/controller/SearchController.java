package com.ymd.rossellamorgante.ymd.controller;

import android.content.Context;

import com.ymd.rossellamorgante.ymd.model.FSPlace;
import com.ymd.rossellamorgante.ymd.viewmodel.FSPlaceViewModel;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

public class SearchController {

    private FSPlaceViewModel viewModel;
    private Context c;

    public  SearchController(Context _c ) {
        viewModel= ViewModelProviders.of((AppCompatActivity) _c).get(FSPlaceViewModel.class);
        viewModel.getVenues(_c);
        c=_c;
    }

    public void  query(String near){
        viewModel.search(near);
    }

    public MutableLiveData<ArrayList<FSPlace>> bindLiveData(){
        return viewModel.getVenues(c);
    }


}
