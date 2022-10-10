package com.example.ldgo.requests;

import com.example.ldgo.entities.FavouriteLocation;
import com.example.ldgo.responses.FavouriteLocationResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllFavouriteLocationRequest {

    ArrayList<FavouriteLocationResponse> data;

    public AllFavouriteLocationRequest(ArrayList<FavouriteLocationResponse> data) {
        this.data = data;
    }

    public ArrayList<FavouriteLocationResponse> getData() {
        return data;
    }

    public ArrayList<FavouriteLocation> getFavouriteLocations() {
        ArrayList<FavouriteLocation> res = null;
        if(data.size() > 0){
            for(FavouriteLocationResponse attr : data) {
                res.add(attr.getAttributes());
            }
        }
        return res;
    }

    public void setData(ArrayList<FavouriteLocationResponse> data) {
        this.data = data;
    }
}