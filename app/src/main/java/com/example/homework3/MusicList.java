package com.example.homework3;

import java.util.Date;

public class MusicList {
    String trackName = null;
    String primaryGenreName = null;
    String artistName = null;

    public String getReleaseDate() {
        return releaseDate;
    }

    String collectionName;
    int trackPrice;
    int collectionPrice;
    String releaseDate;

    public MusicList(String trackName, String artistName, int trackPrice, String releaseDate) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.trackPrice = trackPrice;
        this.releaseDate = releaseDate;
    }

    public MusicList(String trackName, String primaryGenreName, String artistName, String collectionName, int trackPrice, int collectionPrice, String releaseDate) {
        this.trackName = trackName;
        this.primaryGenreName = primaryGenreName;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.trackPrice = trackPrice;
        this.collectionPrice = collectionPrice;
        this.releaseDate = releaseDate;
    }

    public MusicList() {
    }

    public String getTrackName() {
        return trackName;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public int getTrackPrice() {
        return trackPrice;
    }

    public int getCollectionPrice() {
        return collectionPrice;
    }

    @Override
    public String toString() {
        return "MusicList{" +
                "trackName='" + trackName + '\'' +
                ", primaryGenreName='" + primaryGenreName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", collectionName='" + collectionName + '\'' +
                ", trackPrice='" + trackPrice + '\'' +
                ", collectionPrice='" + collectionPrice + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }

}
