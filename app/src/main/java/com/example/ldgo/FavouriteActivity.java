package com.example.ldgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FavouriteActivity extends AppCompatActivity {

    Button btnGoToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        btnGoToHome = findViewById(R.id.btnGoToHome);

        btnGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavouriteActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}