package com.example.ldgo.utils;

public class LdgoHelpers {
    final static int MAX_WIDTH = 400;
    final static String API_KEY = "AIzaSyBPOr1V_ffIyE9VXuVvmAzHJlEEx5mykU4";
    final static String BASE_URL = "https://maps.googleapis.com/maps/api/place/photo";

    public static String googpeMapsImage (String photo_referenc) {
        return BASE_URL + "?maxwidth=" + MAX_WIDTH + "&photo_reference=" + photo_referenc + "&key=" + API_KEY;
    }
}
