package com.example.ldgo.utils;

import com.example.ldgo.entities.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LdgoApi {
    @FormUrlEncoded
    @POST("auth/local")
    Call<User> login(@Field("identifier") String identifier, @Field("password") String password);
}
