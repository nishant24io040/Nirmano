package com.ansrnirmano.models;

public class Feeling {
    public final String name;
    public final String imageUrl;
    public final int imageResource;
    public final Boolean positive;

    public Feeling(String name, String imageUrl, Boolean positive){
        this.name = name;
        this.imageUrl = imageUrl;
        this.imageResource = 0;
        this.positive = positive;
    }

    public Feeling(String name, int imageResource, Boolean positive){
        this.name = name;
        this.imageResource = imageResource;
        this.imageUrl = null;
        this.positive = positive;
    }
}
