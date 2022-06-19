package com.example.cinemaapp.genres;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.cinemaapp.API.MovieRetrofitObject;
import com.example.cinemaapp.API.SingletonRetrofitObject;
import com.example.cinemaapp.ActionAdapter;
import com.example.cinemaapp.Adapter;
import com.example.cinemaapp.HomePageActivity;
import com.example.cinemaapp.R;
import com.example.cinemaapp.Reviews;
import com.example.cinemaapp.models.Items;
import com.example.cinemaapp.models.Movies;
import com.example.cinemaapp.models.Results;
import com.example.cinemaapp.models.VideoPOJO;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Action extends AppCompatActivity {

    //action movies out now
//    private static String JSON_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=f134dfeac1ebb17feefa58d7f94e94cd&language=en-US&page=1&with_genres=28";

    //List<Movies> actionMovies;
    RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        //actionMovies = new ArrayList<>();
        recyclerView = findViewById(R.id.actionRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.hasFixedSize();

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getMovies();
    }

    public void getMovies() {
        Call<Movies> MovieCall = MovieRetrofitObject.getmInstance().getAPI().getActionMovies(
                "f134dfeac1ebb17feefa58d7f94e94cd",
                "en-US",
                1,
                28
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

    }

    private void setRecyclerView(Results[] results) {
        ActionAdapter adapter = new ActionAdapter(this, results);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(Action.this, HomePageActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}