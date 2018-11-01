package com.example.fidel116.firebaseintegration.adpater;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fidel116.firebaseintegration.R;
import com.example.fidel116.firebaseintegration.model.MovieData;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
    private ArrayList<MovieData> movie_data;
    public MovieListAdapter(ArrayList<MovieData> movie_data) {
        this.movie_data = movie_data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movielist_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.tv_movieName.setText("Movie Name :- "+movie_data.get(i).getName());
        myViewHolder.tv_movieRating.setText("Movie Rating :- "+movie_data.get(i).getRating());
        myViewHolder.tv_movieReview.setText("Moview Review :- "+movie_data.get(i).getReview());
    }

    @Override
    public int getItemCount() {
        return movie_data.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_movieName , tv_movieReview, tv_movieRating;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tv_movieName = (TextView) itemView.findViewById(R.id.tv_movieName);
            this.tv_movieRating = (TextView) itemView.findViewById(R.id.tv_rating);
            this.tv_movieReview = (TextView)itemView.findViewById(R.id.tv_review);
        }
    }
}
