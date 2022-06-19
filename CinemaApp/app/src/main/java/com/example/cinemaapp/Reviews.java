package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.example.cinemaapp.API.ReviewRetrofitObject;
import com.example.cinemaapp.models.ReviewPOJO;
import com.example.cinemaapp.models.ReviewResults;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reviews extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        recyclerView = findViewById(R.id.reviewRCV);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        doApiCall();
    }

    private void doApiCall() {
        Call<ReviewPOJO> ReviewPOJOCall = ReviewRetrofitObject.getmInstance().getAPI().getReviews(
                "QJcpJa2p6KHcEMGuoES9FkCm5vDbZ1pb"
        );

        ReviewPOJOCall.enqueue(new Callback<ReviewPOJO>() {
            @Override
            public void onResponse(Call<ReviewPOJO> call, Response<ReviewPOJO> response) {
                setRecyclerView(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ReviewPOJO> call, Throwable t) {

            }
        });
    }

    private void setRecyclerView(ReviewResults[] reviewResults) {
        ReviewAdapter reviewAdapter = new ReviewAdapter(this, reviewResults);
        recyclerView.setAdapter(reviewAdapter);
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
                Intent intent = new Intent(Reviews.this, HomePageActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}