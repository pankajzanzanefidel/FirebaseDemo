package com.example.fidel116.firebaseintegration.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.fidel116.firebaseintegration.R;
import com.example.fidel116.firebaseintegration.model.MovieData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private  MovieData movieData;
    private TextInputEditText movie_name_EditText;
    private TextInputLayout movie_name_InputLayout;
    private RadioGroup rg_review;
    private RadioButton rb_good,rb_veryGood,rb_bad;
    private RatingBar ratingBar;
    private Button btn_save;
    private  DatabaseReference db_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase integration...
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        db_reference = database.getInstance().getReference();

        //widgets intialization...
        movie_name_EditText = (TextInputEditText)findViewById(R.id.movie_name_EditText);
        movie_name_InputLayout = (TextInputLayout)findViewById(R.id.movie_name_InputLayout);
        rg_review = (RadioGroup)findViewById(R.id.rg_review);
        rb_good = (RadioButton)findViewById(R.id.rb_good);
        rb_veryGood = (RadioButton)findViewById(R.id.rb_veryGood);
        rb_bad = (RadioButton)findViewById(R.id.rb_bad);
        ratingBar = (RatingBar)findViewById(R.id.movie_ratingbar);
        btn_save = (Button)findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:{
                if(TextUtils.isEmpty(movie_name_EditText.getText())){
                    movie_name_EditText.setError("Please enter the name");
                    return;
                }

                String review = null;
                int selectedId = rg_review.getCheckedRadioButtonId();
                Log.d("WASTE",String.valueOf(selectedId));

                if(selectedId == R.id.rb_veryGood){
                    review = "Very Good";
                }else if(selectedId == R.id.rb_good){
                    review = "Good";
                }else if (selectedId == R.id.rb_bad){
                    review = "Bad";
                }else{
                    review = null;
                }

                if(review == null){
                    Toast.makeText(this, "Please select the review", Toast.LENGTH_SHORT).show();
                    return;
                }

                float rating = ratingBar.getRating();


                movieData = new MovieData();
                movieData.setMovie_id(movie_name_EditText.getText()+"1");
                movieData.setUser_id("u_1");
                movieData.setName(movie_name_EditText.getText().toString());
                movieData.setRating(rating);
                movieData.setReview(review);
                //Save data to firebase...
                db_reference.child("movies_data").child(movieData.getName()).setValue(movieData);
                Intent intent = new Intent(MainActivity.this,MovieListActivity.class);
                startActivity(intent);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        movie_name_EditText.setText("");
        ratingBar.setRating(0);
    }
}
