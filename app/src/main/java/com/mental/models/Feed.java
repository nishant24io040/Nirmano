package com.mental.models;

public class Feed {
    public final String username;
    public final String feed;
    public final Long time;
    public final String uid;
    public final String id;

    public Feed(String username, String feed, Long time, String uid, String id) {
        this.username = username;
        this.feed = feed;
        this.time = time;
        this.uid = uid;
        this.id = id;
    }
}
