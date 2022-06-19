package com.example.cinemaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.models.Results;
import com.squareup.picasso.Picasso;

public class WatchListAdapter extends RecyclerView.Adapter<WatchListAdapter.WatchListViewHolder>{

    private Context context;
    Results[] results;

    public WatchListAdapter(Context context, Results[] results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public WatchListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.watchlist_items, parent, false);
        return new WatchListAdapter.WatchListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WatchListViewHolder holder, int position) {
        String url = "https://image.tmdb.org/t/p/w500";
        holder.name.setText(results[position].getTitle());
        //issue with contacting string + getPosterpath
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + results[position].getPoster_path()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return results.length;
    }

    public static class WatchListViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView img;

        public WatchListViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.wlName);
            img = itemView.findViewById(R.id.watchlistposters);
        }
    }
}
