package com.example.fidel116.firebaseintegration.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.fidel116.firebaseintegration.R;
import com.example.fidel116.firebaseintegration.adpater.MovieListAdapter;
import com.example.fidel116.firebaseintegration.model.MovieData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private RecyclerView rv_movieList;
    private DatabaseReference db_reference;
    private MovieListAdapter movieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);


        //Firebase integration...
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        db_reference = database.getInstance().getReference();

        rv_movieList = (RecyclerView)findViewById(R.id.rv_movieList);
        Toast.makeText(this, "Successfully added data", Toast.LENGTH_SHORT).show();

        db_reference.child("movies_data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<MovieData> movie_data = new ArrayList<MovieData>();
                for (DataSnapshot movieDataSnapshot : dataSnapshot.getChildren()) {
                    MovieData note = movieDataSnapshot.getValue(MovieData.class);
                    movie_data.add(note);
                }
                if(movie_data.isEmpty()){
                    Toast.makeText(MovieListActivity.this, "No data", Toast.LENGTH_SHORT).show();
                }else{
                    rv_movieList.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(MovieListActivity.this);
                    rv_movieList.setLayoutManager(layoutManager);
                    rv_movieList.setItemAnimator(new DefaultItemAnimator());
                    movieListAdapter = new MovieListAdapter(movie_data);
                    rv_movieList.setAdapter(movieListAdapter);
                }

                Log.d("WASTE",movie_data.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
