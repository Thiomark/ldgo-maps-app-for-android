package com.example.ldgo.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DistanceBetweenLocations {
    ArrayList<String> destination_addresses;
    ArrayList<String> origin_addresses;
    ArrayList<Elements> rows;

    public DistanceBetweenLocations(ArrayList<String> destination_addresses, ArrayList<String> origin_addresses, ArrayList<Elements> rows) {
        this.destination_addresses = destination_addresses;
        this.origin_addresses = origin_addresses;
        this.rows = rows;
    }

    public ArrayList<String> getDestination_addresses() {
        return destination_addresses;
    }

    public void setDestination_addresses(ArrayList<String> destination_addresses) {
        this.destination_addresses = destination_addresses;
    }

    public ArrayList<String> getOrigin_addresses() {
        return origin_addresses;
    }

    public void setOrigin_addresses(ArrayList<String> origin_addresses) {
        this.origin_addresses = origin_addresses;
    }

    public ArrayList<Elements> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Elements> rows) {
        this.rows = rows;
    }

    class Elements {
        Info distance;
        Info duration;

        public Info getDistance() {
            return distance;
        }

        public void setDistance(Info distance) {
            this.distance = distance;
        }

        public Info getDuration() {
            return duration;
        }

        public void setDuration(Info duration) {
            this.duration = duration;
        }

        public Elements(Info distance, Info duration) {
            this.distance = distance;
            this.duration = duration;
        }
    }

    class Info {

        public Info(String text, String value) {
            this.text = text;
            this.value = value;
        }

        String text;
        String value;
    }
}
