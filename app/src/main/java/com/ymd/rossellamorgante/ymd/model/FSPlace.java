package com.ymd.rossellamorgante.ymd.model;
import java.net.MalformedURLException;
import java.util.List;

public class FSPlace {
    public String id;
    public String name;
    public Location location;
    public List<Category> categories;

    public String formatAddress() {

        String fa="";
        for(String s: location.formattedAddress){
            fa+=" "+s;
        }
        return  fa;

        // If I use API 26 , I will use only the below row
        //return String.join(" ", location.formattedAddress);
    }

    public String getIcon() {
        String url ="";

        if(categories.size()>0) {
            url = categories.get(0).icon.prefix+"100"+categories.get(0).icon.suffix;
        }
        return url;
    }


    public class Location {
        public List<String> formattedAddress;
    }

    public class Category {
        public Icon icon;
    }

    public class Icon {
        public String prefix;
        public String suffix;
    }

}

