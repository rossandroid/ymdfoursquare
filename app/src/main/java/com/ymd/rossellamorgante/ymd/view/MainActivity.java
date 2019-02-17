package com.ymd.rossellamorgante.ymd.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ymd.rossellamorgante.ymd.R;
import com.ymd.rossellamorgante.ymd.controller.SearchController;
import com.ymd.rossellamorgante.ymd.model.FSPlace;
import com.ymd.rossellamorgante.ymd.viewmodel.FSPlaceViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public SearchController c;
    ArrayList<FSPlace> venues;
    ListView list;
    VenueAdapter va;

    ProgressBar spinner;
    TextView textSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        venues=new ArrayList<FSPlace>();
        va = new VenueAdapter(this,venues);
        list=((ListView)findViewById(R.id.venueList));
        list.setAdapter(va);
        spinner= findViewById(R.id.spinner);
        textSpinner= findViewById(R.id.textSpinner);

        c = new SearchController( this );
        c.bindLiveData().observeForever (
                new Observer<ArrayList<FSPlace>>() {
                    @Override
                    public void onChanged(@Nullable final ArrayList<FSPlace> _venues) {
                        if(_venues==null){
                            setSpinner(true, true, "Something was gone wrong. Try again!");

                        }else {
                            venues.clear();
                            venues.addAll(_venues);
                            va.notifyDataSetChanged();
                            setSpinner(false, false, "");
                        }

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
                setSpinner(true,false,"Searching...");
                c.query(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                venues.clear();
                va.notifyDataSetChanged();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void setSpinner(boolean show, boolean error, String message){
        if(show){
            list.setVisibility(View.GONE);
            textSpinner.setVisibility(View.VISIBLE);
            textSpinner.setText(message);

            if(error){
                spinner.setVisibility(View.GONE);
            }else
                spinner.setVisibility(View.VISIBLE);

        }else {
            list.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.GONE);
            textSpinner.setVisibility(View.GONE);
        }

    }



}
