package com.ymd.rossellamorgante.ymd.model;

import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class FSPlace {
    public String id;
    public String name;
    public Location location;
    public List<Category> categories;

    public String formatAddress() {

        return location.cc+","+location.city+" "+location.country+" - "+location.postalCode;
    }

    public String getIcon() throws MalformedURLException {
        String url ="";

        if(categories.size()>0) {
            url = categories.get(0).icon.prefix+"100"+categories.get(0).icon.suffix;
        }
        Log.i("Image",url);
        return url;
    }


    public class Location {
        public double lat;
        public double lon;
        public String postalCode;
        public String cc;
        public String city;
        public String state;
        public String country;
    }

    public class Category {
        public Icon icon;
    }

    public class Icon {
        public String prefix;
        public String suffix;
    }

}

