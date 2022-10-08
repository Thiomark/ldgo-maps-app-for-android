package com.example.ldgo.responses;

import com.example.ldgo.entities.Address;

import java.util.ArrayList;
import java.util.List;

public class SearchesResponse {
    ArrayList<String> html_attributions;
    ArrayList<Address> results;

    public SearchesResponse(ArrayList<String> html_attributions, ArrayList<Address> results) {
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
}
