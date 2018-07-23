package com.example.movieinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.movieinfo.Adapters.MoviesAdapter;
import com.example.movieinfo.Model.Movie;
import com.example.movieinfo.Model.MovieResponse;
import com.example.movieinfo.Rest.MovieClient;
import com.example.movieinfo.Rest.MovieInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity implements MoviesAdapter.IndividualMovieClickListener {

    private static final String API_KEY = "8d6d1055a91b79acacfbc113c9c1ad6f";

    private RecyclerView mRecyclerView;

    private MoviesAdapter mMoviesAdapter;

    private List<Movie> mMovies = new ArrayList<>();

    private String queryParameter = "popular";

    // String constants for Intent extras

    public static final String MOVIE_TITLE = "title";
    public static final String MOVIE_POSTER = "poster";
    public static final String MOVIE_RELEASE = "releaseDate";
    public static final String MOVIE_OVERVIEW = "overView";
    public static final String MOVIE_RATING = "rating";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        mRecyclerView = findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new GridLayoutManager(MoviesActivity.this, 2));

        executeSearch(queryParameter);
    }

    public void executeSearch(String parmeter) {

        MovieInterface movieInterface = MovieClient.getClient().create(MovieInterface.class);

        Call<MovieResponse> call = movieInterface.getMovies(parmeter, API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mMovies = response.body().getResults();
                mRecyclerView.setAdapter(new MoviesAdapter(MoviesActivity.this, mMovies,MoviesActivity.this));

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "PROBLEM PARSING MOVIES", Toast.LENGTH_SHORT).show();
                Log.e("EXCETION : ", t.toString());

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.movie_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.popular_movies:
                queryParameter = "popular";
                executeSearch(queryParameter);
                return true;

            case R.id.top_rated:
                queryParameter = "top_rated";
                executeSearch(queryParameter);
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }

    }

    @Override
    public void onListItemClick(int movieItemIndex) {

        Intent movieIntent = new Intent(MoviesActivity.this, MovieDetailsActivity.class);

        Movie mv = mMovies.get(movieItemIndex);

        movieIntent.putExtra(MOVIE_TITLE, mv.getmMovieName());
        movieIntent.putExtra(MOVIE_POSTER, mv.getmImage());
        movieIntent.putExtra(MOVIE_RELEASE, mv.getmReleaseDate());
        movieIntent.putExtra(MOVIE_OVERVIEW, mv.getmOverView());
        movieIntent.putExtra(MOVIE_RATING, mv.getmVoteAverage());

        startActivity(movieIntent);

    }
}


