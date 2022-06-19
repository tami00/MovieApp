package com.example.cinemaapp.booking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.BookingViewHolder> {
    FirebaseUser user;
    Context context;
    private String userID;
    ArrayList<BookingPOJO> bookingList;

    public ViewAdapter(Context context, ArrayList<BookingPOJO> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }


    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_bookings_rcv, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, @SuppressLint("RecyclerView") int position) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            userID = user.getUid();
            BookingPOJO bookings = bookingList.get(position);

            holder.title.setText(bookings.getTitle());
            Picasso.get().load(bookings.getImage()).into(holder.img);
            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String bookingID = bookings.getBookingID();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("watchlist");
                    ref.child(bookingID).removeValue();;
                    ViewBookings.adapter.remove(position);
                }
            });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingID = bookings.getBookingID();
                Boolean confirmed = holder.checkBox.isChecked();
                if(holder.checkBox.isChecked()){
                    holder.checkBox.setChecked(true);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("watchlist").child(bookingID);
                    ref.child("confirmed").setValue(confirmed);
                }
                ViewBookings.adapter.update(bookings,position);
            }
        });



    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView img;
        ImageButton deleteButton;
        CheckBox checkBox;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.RCV_title);
            img = itemView.findViewById(R.id.RCV_img);
            deleteButton = itemView.findViewById(R.id.deleteBtn);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }

    public void remove(int position) {
        bookingList.remove(position);
        notifyItemRemoved(position);
    }

    public void update(BookingPOJO bookings, int position){
        bookingList.set(position, bookings);
        notifyItemChanged(position);
    }
}
