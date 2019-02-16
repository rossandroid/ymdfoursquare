package com.ymd.rossellamorgante.ymd.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ymd.rossellamorgante.ymd.R;
import com.ymd.rossellamorgante.ymd.controller.SearchController;
import com.ymd.rossellamorgante.ymd.model.FSPlace;
import com.ymd.rossellamorgante.ymd.viewmodel.FSPlaceViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.widget.SearchView;


public class MainActivity extends AppCompatActivity {

    SearchController c;
    List<FSPlace> venues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c = new SearchController( this );

        c.bindLiveData().observeForever (
                new Observer<List<FSPlace>>() {
                    @Override
                    public void onChanged(@Nullable final List<FSPlace> _venues) {
                        venues=_venues;
                    }
                }
        );
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
                c.query(query);
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
