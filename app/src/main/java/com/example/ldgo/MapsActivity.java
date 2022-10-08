package com.example.ldgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.ldgo.components.RecyclerAdapter;
import com.example.ldgo.entities.Address;
import com.example.ldgo.responses.SearchesResponse;
import com.example.ldgo.utils.LdgoGoogleMapsApi;
import com.example.ldgo.utils.RetrofitClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Address> fetchedSeaches = new ArrayList<>();
    EditText searchInputField;
    ImageButton goToProfile;
    RecyclerView recyclerViewSearches;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        goToProfile = findViewById(R.id.goToProfile);
        searchInputField = findViewById(R.id.searchInputField);
        recyclerViewSearches = findViewById(R.id.recyclerViewSearches);

        searchInputField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction()==KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    String input = searchInputField.getText().toString();
                     searchForPlaceOnTheMap(input);
                    return true;
                }
                return false;
            }
        });

        goToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void searchForPlaceOnTheMap(String input) {
        LdgoGoogleMapsApi api = RetrofitClient.getRetrofitInstance2().create(LdgoGoogleMapsApi.class);
        Call<SearchesResponse> call = api.searchForPlace(input);
        call.enqueue(new Callback<SearchesResponse>() {
            @Override
            public void onResponse(Call<SearchesResponse> call, Response<SearchesResponse> response) {
                if(response.isSuccessful()){
                    fetchedSeaches = response.body().getResults();
                    RecyclerAdapter adapter = new RecyclerAdapter(fetchedSeaches);
                    RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
                    recyclerViewSearches.setLayoutManager(lm);
                    recyclerViewSearches.setItemAnimator(new DefaultItemAnimator());
                    recyclerViewSearches.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<SearchesResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}