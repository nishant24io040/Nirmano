package com.ansrnirmano.mentalwellness;

public class ModalforMemoryBox {
    private String title,body,date,image,uid,feeling,key;

    public ModalforMemoryBox() {
    }

    public ModalforMemoryBox(String title, String body, String date, String image, String uid,String feeling,String key) {
        this.title = title;
        this.body = body;
        this.date = date;
        this.image = image;
        this.uid = uid;
        this.feeling = feeling;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
