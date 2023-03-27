package com.mental.models;

public class Comment {
    public final String id;
    public final String username;
    public final String comment;
    public final long time;

    public Comment(String id, String username, String comment, long time) {
        this.id = id;
        this.username = username;
        this.comment = comment;
        this.time = time;
    }
}
