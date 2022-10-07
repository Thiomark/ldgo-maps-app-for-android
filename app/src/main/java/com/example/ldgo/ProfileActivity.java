package com.example.ldgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.ldgo.components.LoadingDialogBar;
import com.example.ldgo.entities.User;
import com.example.ldgo.utils.LdgoApi;
import com.example.ldgo.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    ImageButton btnGoToHome;
    TextView username, email, name, language, landmark;
    LinearLayout infoSection;
    CardView btnLogout;
    ToggleButton useMetric;
    private SharedPreferences sp;
    private User user;
    LoadingDialogBar loadingDialogBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        loadingDialogBar = new LoadingDialogBar(this);

        loadingDialogBar.ShowDialog("loading...");

        infoSection = findViewById(R.id.infoSection);
        infoSection.setVisibility(View.GONE);

        btnGoToHome = findViewById(R.id.btnGoToHome);
        btnLogout = findViewById(R.id.btnLogout);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        language = findViewById(R.id.language);
        useMetric = findViewById(R.id.useMetric);
        landmark = findViewById(R.id.landmark);

        sp = getSharedPreferences("user", Context.MODE_PRIVATE);

        LdgoApi ldgoApi = RetrofitClient.getRetrofitInstance().create(LdgoApi.class);
        fetchUser(ldgoApi);

        btnGoToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        useMetric.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                try{
                    user.setUseMetric(isChecked);
                    Call<User> call = ldgoApi.updateUserField(user.getId(), "jim", "jim@jim.com", "jim", isChecked);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(ProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                                Log.d("errrv", response.message());
                                return;
                            }
                            setUser(response.body());
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("errry", t.getMessage());
                        }
                    });
                }catch (Exception e){
                    Log.d("errri", e.getMessage());
                }

            }
        });
    }

    private void logout () {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void fetchUser (LdgoApi ldgoApi) {
        String userID = sp.getString( "userID", "");
        Call<User> call = ldgoApi.getUser(userID);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }

                setUser(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }

    private void setUser(User user){
        this.user = user;
        name.setText(user.getName().toString());
        username.setText(user.getUsername().toString());
        email.setText(user.getEmail().toString());
        language.setText(user.getLanguage().toString());
        landmark.setText(user.getLandmark().toString());
        useMetric.setText(user.getUseMetric() ? "Metric System (kilometres)" : "Imperial System (miles)");
        infoSection.setVisibility(View.VISIBLE);
        loadingDialogBar.HideDialog();
    }
}