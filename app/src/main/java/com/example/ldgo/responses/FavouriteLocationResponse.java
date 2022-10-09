package com.example.ldgo.responses;


import com.example.ldgo.entities.FavouriteLocation;

public class FavouriteLocationResponse {
    int id;
    FavouriteLocation attributes;

    public FavouriteLocationResponse(int id, FavouriteLocation attributes) {
        this.id = id;
        this.attributes = attributes;
    }
}
