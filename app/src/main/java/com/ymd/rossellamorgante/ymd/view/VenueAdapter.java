package com.ymd.rossellamorgante.ymd.view;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ymd.rossellamorgante.ymd.R;
import com.ymd.rossellamorgante.ymd.model.FSPlace;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class VenueAdapter extends ArrayAdapter<FSPlace> {
    private Context mContext;
    private ArrayList<FSPlace> todoList = new ArrayList<FSPlace>();

    public VenueAdapter(@NonNull Context context, ArrayList<FSPlace> list) {
        super(context, 0 , list);
        mContext = context;
        todoList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = LayoutInflater.from(mContext).inflate(R.layout.itemadapter,parent,false);

        FSPlace fs = todoList.get(position);

        TextView venue = ((TextView)listItem.findViewById(R.id.venue));
        TextView address  =  ((TextView)listItem.findViewById(R.id.address));
        ImageView image  =  ((ImageView)listItem.findViewById(R.id.imageVenue));

        image.setColorFilter(mContext.getResources().getColor(R.color.colorFS));


        venue.setText(fs.name);
        address.setText(fs.formatAddress());

        try {
            Picasso.get().load(fs.getIcon()).resize(50, 50) .centerCrop().into(image);
        } catch (MalformedURLException e) {
            // catch exception ->  the default image is shown (search lent)
        }catch (IllegalArgumentException ei){
            // catch exception ->  the default image is shown (search lent)
        }


        return listItem;
    }
}

