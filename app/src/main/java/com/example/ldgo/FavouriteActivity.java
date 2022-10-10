package com.example.ldgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ldgo.components.FavouriteRecyclerAdapter;
import com.example.ldgo.entities.FavouriteLocation;
import com.example.ldgo.entities.User;
import com.example.ldgo.requests.AllFavouriteLocationRequest;
import com.example.ldgo.responses.SearchesResponse;
import com.example.ldgo.utils.LdgoApi;
import com.example.ldgo.utils.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteActivity extends AppCompatActivity {

    Button btnGoToHome;
    ArrayList<FavouriteLocation> savedLocations;
    private SharedPreferences sp;
    private RecyclerView recyclerView;
    LdgoApi ldgoApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        try {
            recyclerView = findViewById(R.id.savedLocationRV);
            sp = getSharedPreferences("user", Context.MODE_PRIVATE);
            ldgoApi = RetrofitClient.getRetrofitInstance().create(LdgoApi.class);
            String jwt = sp.getString( "jwt", "");
            Call<AllFavouriteLocationRequest> call = ldgoApi.getFavouritesLocations(jwt);
            Log.d("xx", jwt);
            call.enqueue(new Callback<AllFavouriteLocationRequest>() {
                @Override
                public void onResponse(Call<AllFavouriteLocationRequest> call, Response<AllFavouriteLocationRequest> response) {
                    if(response.isSuccessful()){
                        savedLocations = response.body().getData();
                        try{
                            for(FavouriteLocation fl : savedLocations){
                                Log.d("ddfd", fl.getName());
                                Log.d("ddfd", fl.getFormatted_address());
                                Log.d("ddfd", fl.getPhoto_reference());
                                Log.d("ddfd", "xxxxxxxxxxxxxxxxxx cvbcvb xxxxxxxxxxx");
                            }
                        }catch (Exception e){
                            Log.d("ddfd", e.getMessage());
                        }

                    }
                }

                @Override
                public void onFailure(Call<AllFavouriteLocationRequest> call, Throwable t) {

                }
            });
        }catch (Exception e) {
            Log.d("sss", e.getMessage());
        }

        setAdapter();

//        btnGoToHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(FavouriteActivity.this, HomeActivity.class);
//                startActivity(intent);
//            }
//        });
    }
    public void setAdapter() {
        try{
//            Logger.dd("section label", savedLocations);
            Log.d("ddd", " "+ savedLocations.get(0).getFormatted_address());
//            FavouriteRecyclerAdapter adapter = new FavouriteRecyclerAdapter(savedLocations);
//            RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
//            recyclerView.setLayoutManager(lm);
//            recyclerView.setItemAnimator(new DefaultItemAnimator());
//            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            Log.d("sss", e.getMessage());
        }

    }
}