package com.example.movieinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView titleTV;
    private TextView releaseTV;
    private TextView overViewTV;
    private TextView ratingTV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        iv = findViewById(R.id.movie_details_poster_id);
        titleTV = findViewById(R.id.movie_details_title_id);
        releaseTV = findViewById(R.id.movie_details_release_date_id);
        overViewTV = findViewById(R.id.movie_details_over_view_id);
        ratingTV = findViewById(R.id.movie_details_rating_id);

        Intent intent = getIntent();

        String image = intent.getStringExtra("poster");
        String title = intent.getStringExtra("title");
        String overView = intent.getStringExtra("overView");
        String releaseDate = intent.getStringExtra("releaseDate");
        double rating = intent.getDoubleExtra("rating",0.0);


        titleTV.setText(title);
        overViewTV.setText(overView);
        releaseTV.setText(releaseDate);
        ratingTV.setText(String.valueOf(rating) + " / 10 ");


        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w500"+ image)
                .into(iv);



    }

}
