package com.example.ldgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private Button mapsBtn, profileBtn, btnGoToFavourites;
    private TextView username;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mapsBtn = findViewById(R.id.btnGoToMaps);
        profileBtn = findViewById(R.id.btnGoToProfile);
        btnGoToFavourites = findViewById(R.id.btnGoToFavourites);
        username = findViewById(R.id.username);

        sp = getSharedPreferences("user", Context.MODE_PRIVATE);

        loadData();

        mapsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnGoToFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FavouriteActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loadData() {
        sp = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String username = sp.getString( "username", "");
        this.username.setText(username);
    }
}