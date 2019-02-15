package com.ymd.rossellamorgante.ymd.model;

import java.util.List;

public class FSPlace {
    public String id;
    public String name;
    public Location location;
    public List<Category> categories;

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

