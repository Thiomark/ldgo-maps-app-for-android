package com.example.ldgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ldgo.entities.User;
import com.example.ldgo.utils.LdgoApi;
import com.example.ldgo.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    Button btnGoToHome;
    TextView username, email, createdAt, name, language, useMetric, landmark;
    private SharedPreferences sp;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        btnGoToHome = findViewById(R.id.btnGoToHome);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        createdAt = findViewById(R.id.createdAt);
        name = findViewById(R.id.name);
        language = findViewById(R.id.language);
        useMetric = findViewById(R.id.useMetric);
        landmark = findViewById(R.id.landmark);

        sp = getSharedPreferences("user", Context.MODE_PRIVATE);

        fetchUser();

        btnGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fetchUser () {
        sp = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
//        String jwt = sp.getString( "jwt", "");
        LdgoApi ldgoApi = RetrofitClient.getRetrofitInstance().create(LdgoApi.class);
        Call<User> call = ldgoApi.getUser("4");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }

                user = response.body();

                name.setText(user.getName().toString());
                username.setText(user.getUsername().toString());
                email.setText(user.getEmail().toString());
                language.setText(user.getLanguage().toString());
                landmark.setText(user.getLandmark().toString());
                useMetric.setText(user.getUseMetric().toString());
                createdAt.setText(user.getCreatedAt().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }
}