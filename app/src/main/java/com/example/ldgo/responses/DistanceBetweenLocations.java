package com.example.ldgo.responses;

import com.example.ldgo.entities.Address;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DistanceBetweenLocations {
    ArrayList<String> html_attributions;
    ArrayList<Address> results;

    public DistanceBetweenLocations(ArrayList<String> html_attributions, ArrayList<Address> results) {
        this.html_attributions = html_attributions;
        this.results = results;
    }

    public ArrayList<String> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(ArrayList<String> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public ArrayList<Address> getResults() {
        return results;
    }

    public void setResults(ArrayList<Address> results) {
        this.results = results;
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
