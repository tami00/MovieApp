package com.example.cinemaapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.models.ReviewResults;
import com.squareup.picasso.Picasso;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewNoteHolder> {

    private Context context;
    ReviewResults[] reviewResults;

    public ReviewAdapter(Context context, ReviewResults[] reviewResults) {
        this.context = context;
        this.reviewResults = reviewResults;
    }

    @NonNull
    @Override
    public ReviewNoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_item, parent, false);
        return new ReviewAdapter.ReviewNoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewNoteHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.title.setText(reviewResults[position].getHeadline());
            holder.desc.setText(reviewResults[position].getSummary_short());
            Picasso.get().load(reviewResults[position].getMultimedia().getSrc()).into(holder.img);

            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = reviewResults[position].getLink().getUrl();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return reviewResults.length;
    }

    public static class ReviewNoteHolder extends RecyclerView.ViewHolder{
        TextView title, desc;
        ImageView img;

        public ReviewNoteHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.reviewTitle);
            desc = itemView.findViewById(R.id.reviewDesc);
            img = itemView.findViewById(R.id.reviewIMG);
        }
    }
}
