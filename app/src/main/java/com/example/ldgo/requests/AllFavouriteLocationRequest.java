package com.example.ldgo.requests;

import com.example.ldgo.entities.FavouriteLocation;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllFavouriteLocationRequest {

    ArrayList<FavouriteLocation> data;
    @SerializedName("data")
    FavouriteLocation data2;

    public AllFavouriteLocationRequest(ArrayList<FavouriteLocation> data) {
        this.data = data;
    }

    public ArrayList<FavouriteLocation> getData() {
        return data;
    }

    public void setData(ArrayList<FavouriteLocation> data) {
        this.data = data;
    }
}