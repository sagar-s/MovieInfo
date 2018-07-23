package com.example.movieinfo.Model;

import com.google.gson.annotations.SerializedName;

public class Movie {


    @SerializedName("vote_average")
    private double mVoteAverage;

    @SerializedName("title")
    private String mMovieName;

    @SerializedName("poster_path")
    private String mImage;

    @SerializedName("backdrop_path")
    private String mBackground;

    @SerializedName("overview")
    private String mOverView;

    @SerializedName("release_date")
    private String mReleaseDate;


    public Movie(double voteAverage, String title, String image, String background, String overView, String releaseDate) {

        mVoteAverage = voteAverage;
        mMovieName = title;
        mImage = image;
        mBackground = background;
        mOverView = overView;
        mReleaseDate = releaseDate;

    }

    public double getmVoteAverage() {
        return mVoteAverage;
    }

    public String getmMovieName() {
        return mMovieName;
    }

    public String getmImage() {
        return mImage;
    }

    public String getmBackground() {
        return mBackground;
    }

    public String getmOverView() {
        return mOverView;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

}
