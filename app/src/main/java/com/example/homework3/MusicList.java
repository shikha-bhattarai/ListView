package com.example.homework3;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class MusicList implements Serializable {
    String trackName = null;
    String primaryGenreName = null;
    String artistName = null;
    String artworkUrl100 = null;
    String collectionName;
    int trackPrice;
    int collectionPrice;
    String releaseDate;
    Date convertedDate;

    public MusicList(String trackName, String primaryGenreName, String artistName, String collectionName, int trackPrice, int collectionPrice, String releaseDate, String artworkUrl30) {
        this.trackName = trackName;
        this.primaryGenreName = primaryGenreName;
        this.artistName = artistName;
        this.collectionName = collectionName;
        this.trackPrice = trackPrice;
        this.collectionPrice = collectionPrice;
        this.releaseDate = releaseDate;
        this.artworkUrl100 = artworkUrl30;
    }

    public MusicList() {
    }

    public String getTrackName() {
        return trackName;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Date getConvertedDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(releaseDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertedDate;
    }


    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }
    public String getartworkUrl100() {
        return artworkUrl100;
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
                ", artworkUrl30='" + artworkUrl100 + '\'' +
                '}';
    }

}
