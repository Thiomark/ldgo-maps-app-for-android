package com.example.ldgo.entities;

public class FavouriteLocation {
    int id;
    Attributes attributes;

    public FavouriteLocation(int id, Attributes attributes) {
        this.id = id;
        this.attributes = attributes;
    }
}

class Attributes {
    String name;
    String longitudes;
    String latitudes;
    String createdAt;
    String formatted_address;
    String photo_reference;
    String place_id;

    public Attributes(String name, String longitudes, String latitudes, String createdAt, String formatted_address, String photo_reference, String place_id) {
        this.name = name;
        this.longitudes = longitudes;
        this.latitudes = latitudes;
        this.createdAt = createdAt;
        this.formatted_address = formatted_address;
        this.photo_reference = photo_reference;
        this.place_id = place_id;
    }
}
