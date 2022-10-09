package com.example.ldgo.utils;

import com.example.ldgo.entities.User;
import com.example.ldgo.entities.UserLogin;
import com.example.ldgo.requests.FavouriteLocationRequest;
import com.example.ldgo.responses.FavouriteLocationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LdgoApi {
    @FormUrlEncoded
    @POST("auth/local")
    Call<UserLogin> login(@Field("identifier") String identifier,
                          @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/local/register")
    Call<UserLogin> register(@Field("name") String name,
                     @Field("email") String email,
                     @Field("username") String username,
                     @Field("password") String password);

    @GET("users/{id}")
    Call<User> getUser(@Path("id") String id);

    @POST("favourites")
    Call<FavouriteLocationResponse> addFavouritesLocations(@Header("Authorization") String token,
                                                           @Body FavouriteLocationRequest favourite);

    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") String id, @Body User user);

    @GET("users/me")
    Call<User> getMe(@Header("Authorization") String token);

    @FormUrlEncoded
    @PUT("users/{id}")
    Call<User> updateUserField(@Path("id") String id, @Field("name") String name,
                        @Field("email") String email,
                        @Field("username") String username,
                        @Field("useMetric") Boolean useMetric);
}
