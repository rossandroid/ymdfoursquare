package com.ymd.rossellamorgante.ymd.controller;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.ymd.rossellamorgante.ymd.model.FSPlace;
import com.ymd.rossellamorgante.ymd.viewmodel.FSPlaceViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class SearchController {

    private FSPlaceViewModel viewModel;
    private AppCompatActivity c;

    public  SearchController( AppCompatActivity _c ) {
        viewModel= ViewModelProviders.of(_c).get(FSPlaceViewModel.class);
        viewModel.getVenues(_c);
        c=_c;
    }

    public void  query(String near){
        viewModel.search(near);
    }

    public MutableLiveData<List<FSPlace>> bindLiveData(){
        return viewModel.getVenues(c);
    }
}
