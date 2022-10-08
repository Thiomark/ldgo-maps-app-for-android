package com.example.ldgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ldgo.entities.User;
import com.example.ldgo.entities.UserLogin;
import com.example.ldgo.utils.LdgoApi;
import com.example.ldgo.utils.RetrofitClient;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnLogin;
    private TextView btnSignUp;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        sp = getSharedPreferences("user", Context.MODE_PRIVATE);

        loadData();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSendLoginRequest();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void btnSendLoginRequest(){
        if (TextUtils.isEmpty(inputEmail.getText().toString()) || TextUtils.isEmpty(inputPassword.getText().toString())){
            Toast.makeText(LoginActivity.this, "Please enter all fields!!", Toast.LENGTH_LONG).show();
        }else{
            LdgoApi ldgoApi = RetrofitClient.getRetrofitInstance().create(LdgoApi.class);
            Call<UserLogin> call = ldgoApi.login(inputEmail.getText().toString(), inputPassword.getText().toString());
            call.enqueue(new Callback<UserLogin>() {
                @Override
                public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                    if (!response.isSuccessful()) {
                        Log.d("log-error", response.message());
                        Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    saveData(response.body().getUser().getUsername(), response.body().getUser().getId(), response.body().getJwt());
                }

                @Override
                public void onFailure(Call<UserLogin> call, Throwable t) {
                    Log.d("log-fail", t.getMessage());
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
                }
            });
        }
    }

    public void saveData(String username, String userID, String jwt) {
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("username", username);
        editor.putString("userID", userID);
        editor.putString("jwt", "Bearer "+ jwt);
        editor.commit();

        loadData();
    }

    public void loadData() {
        sp = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String jwt = sp.getString( "jwt", "");

        if(jwt != null && !jwt.trim().isEmpty()) {
            LdgoApi ldgoApi = RetrofitClient.getRetrofitInstance().create(LdgoApi.class);
            Call<User> call = ldgoApi.getMe(jwt);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (!response.isSuccessful()) {
                        return;
                    }
                    Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
                }
            });

        }
    }
}