package com.example.ldgo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Constraints;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import com.example.ldgo.components.RecyclerAdapter;
import com.example.ldgo.entities.Address;
import com.example.ldgo.entities.FavouriteLocation;
import com.example.ldgo.entities.User;
import com.example.ldgo.requests.FavouriteLocationRequest;
import com.example.ldgo.responses.DistanceBetweenLocations;
import com.example.ldgo.responses.FavouriteLocationResponse;
import com.example.ldgo.responses.SearchesResponse;
import com.example.ldgo.utils.LdgoApi;
import com.example.ldgo.utils.LdgoGoogleMapsApi;
import com.example.ldgo.utils.LdgoHelpers;
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

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;

import java.util.ArrayList;
import java.util.List;

public class DirectionsActivity extends FragmentActivity implements OnMapReadyCallback {

    User user;
    private GoogleMap mMap;
    private String TAG = "so47492459";
    private SharedPreferences sp;
    private ArrayList<Address> fetchedSeaches = new ArrayList<>();
    EditText originInput, destinationInput;
    TextView timeItTakes, distanceTextView;
    CardView startButton;

    public String INITIAL_DESTINATION;
    public String INITIAL_LONGITUDE;
    public String INITIAL_LATITUDE;

    public String FINAL_DESTINATION;
    public String FINAL_LONGITUDE;
    public String FINAL_LATITUDE;

    LdgoGoogleMapsApi googleMapsApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.directions);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        googleMapsApi = RetrofitClient.getRetrofitInstance2().create(LdgoGoogleMapsApi.class);

        sp = getSharedPreferences("user", Context.MODE_PRIVATE);

        Intent intent = getIntent();

        fetchUser();

        originInput = findViewById(R.id.origin);
        destinationInput = findViewById(R.id.destination);

        startButton = findViewById(R.id.startButton);

        distanceTextView = findViewById(R.id.distanceTextView);
        timeItTakes = findViewById(R.id.timeItTakes);

        INITIAL_DESTINATION = intent.getStringExtra(MapsActivity.INITIAL_DESTINATION);
        INITIAL_LONGITUDE = intent.getStringExtra(MapsActivity.INITIAL_LONGITUDE);
        INITIAL_LATITUDE = intent.getStringExtra(MapsActivity.INITIAL_LATITUDE);

        FINAL_DESTINATION = intent.getStringExtra(MapsActivity.FINAL_DESTINATION);
        FINAL_LATITUDE = intent.getStringExtra(MapsActivity.FINAL_LATITUDE);
        FINAL_LONGITUDE = intent.getStringExtra(MapsActivity.FINAL_LONGITUDE);

        originInput.setText(INITIAL_DESTINATION);
        destinationInput.setText(FINAL_DESTINATION);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchForLocation();
            }
        });
    }

    public void searchForLocation() {

        String myLocation = originInput.getText().toString();
        String finalDestination = destinationInput.getText().toString();

        try{
            String unit = user.getUseMetric() ? "metric" : "imperial";
            Call<DistanceBetweenLocations> call = googleMapsApi.getDistanceBetweenLocations(unit , finalDestination, myLocation);
            call.enqueue(new Callback<DistanceBetweenLocations>() {
                @Override
                public void onResponse(Call<DistanceBetweenLocations> call, Response<DistanceBetweenLocations> response) {
                    if (response.isSuccessful()){
                        try{
                            distanceTextView.setText(response.body().getRows().get(0).getElements().get(0).getDistance().getText());
                            timeItTakes.setText(response.body().getRows().get(0).getElements().get(0).getDuration().getText());
                            originInput.setText(response.body().getOrigin_addresses().get(0));
                            destinationInput.setText(response.body().getDestination_addresses().get(0));
                            getLocations();
//                            getLocations(finalDestination, -33.9248685, 18.4240553);
//                            searchForPlaceOnTheMap(response.body().getOrigin_addresses().get(0));
                        }catch (Exception e){
                            Toast.makeText(DirectionsActivity.this, "Not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<DistanceBetweenLocations> call, Throwable t) {

                }
            });
        }catch(Exception e){

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLocations();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void getLocations(){
        double myLatitudes = Double.parseDouble(INITIAL_LATITUDE);
        double myLongitude = Double.parseDouble(INITIAL_LONGITUDE);
        String myLocation = INITIAL_DESTINATION;

        Double finalLongitudes = Double.parseDouble(FINAL_LATITUDE);
        Double finalLatitudes = Double.parseDouble(FINAL_LONGITUDE);
        String finalDestination = FINAL_DESTINATION;

        try {
            showLocationOnMaps(myLocation, myLongitude, myLatitudes, finalDestination, finalLatitudes, finalLongitudes);
        }catch (Exception e) {
        }
    }

    public void showLocationOnMaps(String myLocation, Double myLongitude, Double myLatitudes, String finalDestination, Double finalLatitudes, Double finalLongitudes){
        try{
            LatLng barcelona = new LatLng(myLatitudes, myLongitude);
            mMap.addMarker(new MarkerOptions().position(barcelona).title(myLocation));

            LatLng madrid = new LatLng(finalLatitudes, finalLongitudes);
            mMap.addMarker(new MarkerOptions().position(madrid).title(finalDestination));

            //Define list to get all latlng for the route
            List<LatLng> path = new ArrayList<>();


            //Execute Directions API request
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey("AIzaSyBPOr1V_ffIyE9VXuVvmAzHJlEEx5mykU4")
                    .build();
            DirectionsApiRequest req = DirectionsApi.getDirections(context, myLocation, finalDestination);
            try {
                DirectionsResult res = req.await();

                //Loop through legs and steps to get encoded polylines of each step
                if (res.routes != null && res.routes.length > 0) {
                    DirectionsRoute route = res.routes[0];

                    if (route.legs !=null) {
                        for(int i=0; i<route.legs.length; i++) {
                            DirectionsLeg leg = route.legs[i];
                            if (leg.steps != null) {
                                for (int j=0; j<leg.steps.length;j++){
                                    DirectionsStep step = leg.steps[j];
                                    if (step.steps != null && step.steps.length >0) {
                                        for (int k=0; k<step.steps.length;k++){
                                            DirectionsStep step1 = step.steps[k];
                                            EncodedPolyline points1 = step1.polyline;
                                            if (points1 != null) {
                                                //Decode polyline and add points to list of route coordinates
                                                List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                for (com.google.maps.model.LatLng coord1 : coords1) {
                                                    path.add(new LatLng(coord1.lat, coord1.lng));
                                                }
                                            }
                                        }
                                    } else {
                                        EncodedPolyline points = step.polyline;
                                        if (points != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords = points.decodePath();
                                            for (com.google.maps.model.LatLng coord : coords) {
                                                path.add(new LatLng(coord.lat, coord.lng));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch(Exception ex) {
                Log.e(TAG, ex.getLocalizedMessage());
            }

            //Draw the polyline
            if (path.size() > 0) {
                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLUE).width(5);
                mMap.addPolyline(opts);
            }

            mMap.getUiSettings().setZoomControlsEnabled(true);

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(barcelona, 8));
        }catch(Exception e) {}
    }

    private void searchForPlaceOnTheMap(String input) {
        Call<SearchesResponse> call = googleMapsApi.searchForPlace(input);
        call.enqueue(new Callback<SearchesResponse>() {
            @Override
            public void onResponse(Call<SearchesResponse> call, Response<SearchesResponse> response) {
                if(response.isSuccessful()){
                    fetchedSeaches = response.body().getResults();
                }
            }

            @Override
            public void onFailure(Call<SearchesResponse> call, Throwable t) {

            }
        });
    }

    private void fetchUser () {
        LdgoApi ldgoApi = RetrofitClient.getRetrofitInstance().create(LdgoApi.class);
        String userID = sp.getString( "userID", "");
        Call<User> call = ldgoApi.getUser(userID);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}