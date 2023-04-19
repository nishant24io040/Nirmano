package com.nirman.models;

public class Negative {
    public final int id;
    public final String name;
    public final String imageUrl;
    public final int imageResource;
    public final String duration;
    public final Class act;
    public final String emotion;

    public Negative(int id, String name, String imageUrl, String duration,Class act,String emotion){
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.imageResource = 0;
        this.duration = duration;
        this.act = act;
        this.emotion =emotion;
    }

    public Negative(int id, String name, int imageResource, String duration, Class act,String emotion){
        this.id = id;
        this.name = name;
        this.imageResource = imageResource;
        this.imageUrl = null;
        this.duration = duration;
        this.emotion = emotion;
        this.act =act;
    }
}
