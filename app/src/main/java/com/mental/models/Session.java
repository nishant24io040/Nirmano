package com.mental.models;

import android.net.Uri;

public class Session {
    public final String heading;
    public final String subHeading;
    public final String date;
    public final Uri imageUrl;
    public final int imageResource;
    public final String docID;
    public final boolean feedbackStart;
    public final String prof;

    public Session(String heading, String subHeading, String date, Uri imageUrl,String docID,boolean feedbackStart,String prof){
        this.heading = heading;
        this.subHeading = subHeading;
        this.imageUrl = imageUrl;
        this.imageResource = 0;
        this.date = date;
        this.docID = docID;
        this.feedbackStart = feedbackStart;
        this.prof = prof;
    }

    public Session(String heading, String subHeading, String date, int imageResource,String docID,boolean feedbackStart,String prof){
        this.heading = heading;
        this.subHeading = subHeading;
        this.imageResource = imageResource;
        this.imageUrl = null;
        this.date = date;
        this.docID = docID;
        this.feedbackStart = feedbackStart;
        this.prof = prof;
    }
}
