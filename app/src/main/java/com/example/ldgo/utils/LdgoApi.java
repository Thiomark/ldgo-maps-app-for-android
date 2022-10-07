package com.example.ldgo.utils;

import com.example.ldgo.entities.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LdgoApi {
    @FormUrlEncoded
    @POST("auth/local")
    Call<User> login(@Field("identifier") String identifier,
                     @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/local/register")
    Call<User> register(@Field("name") String name,
                     @Field("email") String email,
                     @Field("username") String username,
                     @Field("password") String password);

    @GET("users/{id}")
    Call<User> getUser(@Path("id") String id);
}
