package com.example.cinemaapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinemaapp.booking.ActionBooking;
import com.example.cinemaapp.genres.Action;
import com.example.cinemaapp.models.Movies;
import com.example.cinemaapp.models.Results;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActionAdapter extends RecyclerView.Adapter<ActionAdapter.ActionViewHolder> {

    private Context context;
    Results[] results;

    public ActionAdapter(Context context, Results[] results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ActionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.action_movies_item, parent, false);
        return new ActionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String url = "https://image.tmdb.org/t/p/w500";
        holder.name.setText(results[position].getTitle());
        //issue with contacting string + getPosterpath
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + results[position].getPoster_path()).into(holder.img);
        //Glide.with(context).load("http://image.tmdb.org/t/p/w500/o4I5sHdjzs29hBWzHtS2MKD3JsM.jpg").into(holder.img);
        //Log.i("TAG", url + results[position].getPoster_path());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actionIntent = new Intent(context, ActionBooking.class);
                actionIntent.putExtra(("movieID"), results[position].getId());
                actionIntent.putExtra(("poster"), url + results[position].getPoster_path());
                actionIntent.putExtra(("desc"), results[position].getOverview());
                actionIntent.putExtra(("title"), results[position].getTitle());
                context.startActivity(actionIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.length;
    }

    public static class ActionViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView img;

        public ActionViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.actionName);
            img = itemView.findViewById(R.id.actionPosters);
        }
    }
}
