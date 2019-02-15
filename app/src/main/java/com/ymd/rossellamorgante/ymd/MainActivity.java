package com.ymd.rossellamorgante.ymd;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ymd.rossellamorgante.ymd.model.FSPlace;
import com.ymd.rossellamorgante.ymd.view.FSPlaceViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;


public class MainActivity extends AppCompatActivity {

    FSPlaceViewModel viewModel;
    List<FSPlace> venues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel=ViewModelProviders.of(this).get(FSPlaceViewModel.class);

        viewModel.getVenues(this).observeForever (
                new Observer<List<FSPlace>>() {
                    @Override
                    public void onChanged(@Nullable final List<FSPlace> _venues) {
                        // Update the UI, in this case, a TextView.
                        venues=_venues;
                    }
                }
        );
    }



    public void searchEvent(View v){


        viewModel.search("staines");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu, menu);

        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);

        final SearchView searchView = (SearchView) searchViewItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();


                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Log.i("SearchViewnewText",newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }



}
