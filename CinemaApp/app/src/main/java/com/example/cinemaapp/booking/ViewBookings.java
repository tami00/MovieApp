package com.example.cinemaapp.booking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.cinemaapp.HomePageActivity;
import com.example.cinemaapp.R;
import com.example.cinemaapp.genres.Action;
import com.example.cinemaapp.models.Movies;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewBookings extends AppCompatActivity {
    RecyclerView recyclerView;
    static ViewAdapter adapter;
    ArrayList<BookingPOJO> bookingList;
    FirebaseUser user;
    DatabaseReference ref;
    private String userID;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);

        recyclerView = findViewById(R.id.viewRCV);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.hasFixedSize();

        bookingList = new ArrayList<BookingPOJO>();

        adapter = new ViewAdapter(ViewBookings.this, bookingList);
        recyclerView.setAdapter(adapter);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        EventChangeListener();
    }

    private void EventChangeListener() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("watchlist");
        userID = user.getUid();


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookingList.clear();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    BookingPOJO bookings = userSnapshot.getValue(BookingPOJO.class);
                    if (userID.equalsIgnoreCase(bookings.getUserID())) {
                        bookingList.add(bookings);
                    }
                }
                adapter.notifyItemInserted(bookingList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("DBError", "Cancel Access DB");
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
                Intent intent = new Intent(ViewBookings.this, HomePageActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}