package com.example.movieinfo.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieinfo.Model.Movie;
import com.example.movieinfo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private Context mContext;
    private List<Movie> mMovies;
    private IndividualMovieClickListener mMovieClickListener;

    public interface IndividualMovieClickListener {
        void onListItemClick(int movieItemIndex);
    }

//    public void setOnItemClickListener(IndividualMovieClickListener listener) {
//        mMovieClickListener = listener;
//    }


    public MoviesAdapter(Context context, List<Movie> movies, IndividualMovieClickListener listener) {
        mContext = context;
        mMovies = movies;
        mMovieClickListener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // creating the view

        View view;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        view = inflater.inflate(R.layout.movie_card, parent, false);

        MovieViewHolder movieViewHolder = new MovieViewHolder(view);

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        holder.movieTitle.setText(mMovies.get(position).getmMovieName());

        Picasso.with(mContext)
                .load("http://image.tmdb.org/t/p/w500" + mMovies.get(position).getmImage())
                .into(holder.movieImage);


    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView movieTitle;
        private ImageView movieImage;
        private CardView movieCard;

        public MovieViewHolder(View itemView) {
            super(itemView);
            movieCard = itemView.findViewById(R.id.card_view_id);
            movieImage = itemView.findViewById(R.id.movie_image_id);
            movieTitle = itemView.findViewById(R.id.movie_title_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (mMovieClickListener != null) {
                int moviePosition = getAdapterPosition();
                if (moviePosition != RecyclerView.NO_POSITION) // makes sure position is still valid in Recycler View
                {
                    mMovieClickListener.onListItemClick(moviePosition);
                }
            }

        }
    }
}

