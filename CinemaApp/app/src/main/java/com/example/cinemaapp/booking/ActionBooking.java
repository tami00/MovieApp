package com.example.cinemaapp.booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinemaapp.HomePageActivity;
import com.example.cinemaapp.R;
import com.example.cinemaapp.Reviews;
import com.example.cinemaapp.models.Movies;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class ActionBooking extends AppCompatActivity {

    ImageView poster;
    TextView desc, actionTitle;
    ImageButton add;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_booking);
        actionTitle = findViewById(R.id.bookingAtitle);
        poster = findViewById(R.id.bookingAP);
        desc = findViewById(R.id.bookingdescAP);
        add = findViewById(R.id.add);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        String description = (String) bundle.get("desc");
        String title = (String) bundle.get("title");

        desc.setText(description);
        actionTitle.setText(title);

        //Picasso.get().load("https://image.tmdb.org/t/p/w500/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg").into(poster);
        Picasso.get().load(bundle.getString("poster")).into(poster);
        //Log.i("Tag",bundle.getString("poster"));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = (String) bundle.get("title");
                String id = (String) bundle.get("movieID");
                String img = (String) bundle.getString("poster");

                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String bookingID = UUID.randomUUID().toString();

                //possibly turn to int
                //String quantity = String.valueOf(displayInteger.getText());

                BookingPOJO movies = new BookingPOJO(title, id, userID, img, bookingID, false);

                //send poster image

                FirebaseDatabase.getInstance().getReference("watchlist").child(bookingID).setValue(movies)
                        .addOnCompleteListener(task1 -> {
                            if(task1.isSuccessful()) {
                                Toast.makeText(ActionBooking.this, "You have added to your watchlist", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(ActionBooking.this, "Error adding movie, please try again", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
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
                Intent intent = new Intent(ActionBooking.this, HomePageActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}