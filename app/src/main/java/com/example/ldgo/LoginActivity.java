package com.example.ldgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    //Creating controls in memory, for referencing whats on our layout
    EditText inputEmail;
    EditText inputPassword;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Linking and Assigning data/values/functionality to our created controls
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnlogin = findViewById(R.id.btnlogin);

        //Assigning an eventListener to Button
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputEmail.getText().toString().equals("1234") && inputPassword.getText().toString().equals("1234"))
                {
                    //Opening the next activity
                    Intent intent = new Intent(LoginActivity.this,MapsActivity.class);
                    //startActivity(intent);
                    startActivity(intent);

                    //A message is displayed to user giving them feedback
                    Toast.makeText(LoginActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}