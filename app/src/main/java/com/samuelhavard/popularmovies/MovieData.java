package com.samuelhavard.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by samue_000 on 10/29/2015.
 */
public class MovieData implements Parcelable {
    String mTitle;
    String mPlot;
    String mRating;
    String mPopularity;
    String mDate;
    String mImage;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getPlot() {
        return mPlot;
    }

    public void setPlot(String plot) {
        this.mPlot = plot;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        this.mRating = rating;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public String getPopularity() {
        return mPopularity;
    }

    public void setPopularity(String popularity) {
        this.mPopularity = popularity;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mPlot);
        dest.writeString(mRating);
        dest.writeString(mPopularity);
        dest.writeString(mDate);
        dest.writeString(mImage);
    }

    private MovieData (Parcel in) {
        mTitle = in.readString();
        mPlot = in.readString();
        mRating = in.readString();
        mPopularity = in.readString();
        mDate = in.readString();
        mImage = in.readString();
    }

    public static final Creator<MovieData> CREATOR = new Creator<MovieData>() {
        @Override
        public MovieData createFromParcel(Parcel source) {
            return new MovieData(source);
        }

        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };
}
