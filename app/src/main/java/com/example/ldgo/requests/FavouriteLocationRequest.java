package com.example.ldgo.requests;

import com.example.ldgo.entities.FavouriteLocation;
import com.google.gson.annotations.SerializedName;

public class FavouriteLocationRequest {
    @SerializedName("data")
    FavouriteLocation data;

    public FavouriteLocationRequest(FavouriteLocation data) {
        this.data = data;
    }

    public FavouriteLocation getData() {
        return data;
    }

    public void setData(FavouriteLocation data) {
        this.data = data;
    }
}
