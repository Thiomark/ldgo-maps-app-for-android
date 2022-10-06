package com.example.ldgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ldgo.entities.User;
import com.example.ldgo.utils.LdgoApi;
import com.example.ldgo.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView btnSignIn;
    private Button btnRegister;
    private EditText inputEmail, inputPassword, inputUsername, inputName;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignIn = findViewById(R.id.btnSignIn);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputUsername = findViewById(R.id.inputUsername);
        inputName = findViewById(R.id.inputName);
        btnRegister = findViewById(R.id.btnRegister);

        sp = getSharedPreferences("user", Context.MODE_PRIVATE);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser(){
        if (TextUtils.isEmpty(inputEmail.getText().toString()) ||
                TextUtils.isEmpty(inputPassword.getText().toString()) ||
                TextUtils.isEmpty(inputUsername.getText().toString()) ||
                TextUtils.isEmpty(inputName.getText().toString())
        ){
            Toast.makeText(this, "Please enter all fields!!", Toast.LENGTH_LONG).show();
        }else{
            LdgoApi ldgoApi = RetrofitClient.getRetrofitInstance().create(LdgoApi.class);
            String name = inputEmail.getText().toString();
            String email = inputEmail.getText().toString();
            String username = inputEmail.getText().toString();
            String password = inputEmail.getText().toString();
            Call<User> call = ldgoApi.register(name, email, username, password);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String fetchedJwt = response.body().getJwt();
                    String fetchedUsername = "ChangeName";

                    saveData(fetchedJwt, "ChangeName");
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
                }
            });
        }
    }

    public void saveData(String jwt, String username) {
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("jwt", jwt);
        editor.putString("username", username);
        editor.commit();

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }
}