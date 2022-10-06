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
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Linking and Assigning data/values/functionality to our created controls
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        btnlogin = findViewById(R.id.btnlogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        //Assigning an eventListener to Button
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              if (inputEmail.getText().toString().equals("1234") && inputPassword.getText().toString().equals("1234"))

                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);

                Toast.makeText(LoginActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();

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
}