package com.example.ldgo.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DistanceBetweenLocations {
    ArrayList<String> destination_addresses;
    ArrayList<String> origin_addresses;
    ArrayList<Elements> rows;

    class Elements {
        Info distance;
        Info duration;
    }

    class Info {
        String text;
        String value;
    }
}
