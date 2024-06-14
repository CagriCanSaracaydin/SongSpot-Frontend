package com.example.myapplication;

import android.widget.TextView;

import androidx.annotation.Nullable;

public class Rating {
    private String songId;
    private String username;
    private int rating;
    private String timeStamp;

    // Getters and Setters
    public String getSongId() { return songId; }
    public Rating(String songId, String username, int rating, String timeStamp) {
        this.songId = songId;
        this.username = username;
        this.rating = rating;
        this.timeStamp = timeStamp;
    }
    public void setSongId(String songId) { this.songId = songId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getTimestamp() { return timeStamp; }
    public void setTimestamp(String timestamp) { this.timeStamp = timestamp; }
}
