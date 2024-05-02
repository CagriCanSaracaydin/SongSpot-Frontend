package com.example.myapplication;

public class Comment {
    private String id;
    private String songId;
    private String username;
    private String comment;
    private String timeStamp;

    public Comment(String songId, String username, String comment, String timeStamp) {
        this.songId = songId;
        this.username = username;
        this.comment = comment;
        this.timeStamp = timeStamp;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSongId() { return songId; }
    public void setSongId(String songId) { this.songId = songId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getTimeStamp() { return timeStamp; }
    public void setTimeStamp(String timeStamp) { this.timeStamp = timeStamp; }
}
