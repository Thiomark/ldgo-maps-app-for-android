package com.example.ldgo.utils;
import com.example.ldgo.responses.SearchesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LdgoGoogleMapsApi {
    @GET("textsearch/json?key=AIzaSyBPOr1V_ffIyE9VXuVvmAzHJlEEx5mykU4")
    Call<SearchesResponse> searchForPlace(@Query("query") String query);
}