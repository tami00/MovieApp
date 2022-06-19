package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.cinemaapp.API.MovieRetrofitObject;
import com.example.cinemaapp.API.MovieSearchRetrofit;
import com.example.cinemaapp.booking.ActionBooking;
import com.example.cinemaapp.models.Movies;
import com.example.cinemaapp.models.Results;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WatchListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);

        Toolbar toolbar = findViewById(R.id.watchlisttoolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.wlRCV);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.hasFixedSize();

        toolbar = findViewById(R.id.watchlisttoolbar);
        setSupportActionBar(toolbar);

        //getAllMovies();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search_bar).getActionView();
        MenuItem menuItem = menu.findItem(R.id.search_bar);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search Movies...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Call<Movies> MovieCall = MovieSearchRetrofit.getmInstance().getAPI().getSearch(
                        "f134dfeac1ebb17feefa58d7f94e94cd",
                        "en-US",
                        query
                );

                MovieCall.enqueue(new Callback<Movies>() {
                    @Override
                    public void onResponse(Call<Movies> call, Response<Movies> response) {
                        setRecyclerView(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<Movies> call, Throwable t) {

                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(WatchListActivity.this, HomePageActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public void getAllMovies() {
//        Call<Movies> MovieCall = MovieRetrofitObject.getmInstance().getAPI().getAllMovies(
//                "f134dfeac1ebb17feefa58d7f94e94cd",
//                "en-US",
//                1
//        );
//
//        MovieCall.enqueue(new Callback<Movies>() {
//            @Override
//            public void onResponse(Call<Movies> call, Response<Movies> response) {
//                setRecyclerView(response.body().getResults());
//            }
//
//            @Override
//            public void onFailure(Call<Movies> call, Throwable t) {
//
//            }
//        });
//
//    }

    private void setRecyclerView(Results[] results) {
        WatchListAdapter adapter = new WatchListAdapter(this, results);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
    }
}