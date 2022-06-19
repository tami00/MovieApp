package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.cinemaapp.API.SingletonRetrofitObject;
//import com.example.cinemaapp.models.ResponseVideos;
//import com.example.cinemaapp.models.Video;
import com.example.cinemaapp.booking.ViewBookings;
import com.example.cinemaapp.genres.Action;
import com.example.cinemaapp.models.Items;
import com.example.cinemaapp.models.VideoPOJO;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_menu);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_createBooking:
                        Intent createIntent = new Intent(HomePageActivity.this, Action.class);
                        startActivity(createIntent);
                        break;
                    case R.id.nav_viewBookings:
                        Intent viewIntent = new Intent(HomePageActivity.this, ViewBookings.class);
                        startActivity(viewIntent);
                        break;
                    case R.id.menu_maps:
                        Intent mapIntent = new Intent(HomePageActivity.this, MapsActivity.class);
                        startActivity(mapIntent);
                        break;
                    case R.id.menu_reviews:
                        Intent reviewIntent = new Intent(HomePageActivity.this, Reviews.class);
                        startActivity(reviewIntent);
                        break;
                    case R.id.menu_watchlist:
                        Intent searchIntent = new Intent(HomePageActivity.this, WatchListActivity.class);
                        startActivity(searchIntent);
                        break;
                    case R.id.nav_logout:
                        logoutFirebase();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        doApiCall();
    }

    private void logoutFirebase() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        startActivity(new Intent(HomePageActivity.this, WelcomeActivity.class));
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void doApiCall() {
        Call<VideoPOJO> VideoPOJOCall = SingletonRetrofitObject.getmInstance().getAPI().getAllVideos(
              "10",
                "PL6tKg802I44IlniEWUBuwm8NOwSpT26BM",
                "AIzaSyCkww9Jl7RO7Dwb8jDdROHYLu97JNIwFUY"
        );

        VideoPOJOCall.enqueue(new Callback<VideoPOJO>() {
            @Override
            public void onResponse(Call<VideoPOJO> call, Response<VideoPOJO> response) {
                setRecyclerView(response.body().getItems());
            }

            @Override
            public void onFailure(Call<VideoPOJO> call, Throwable t) {

            }
        });
    }

    private void setRecyclerView(Items[] items) {
        Adapter myAdapter = new Adapter(this, items);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
