package com.ansrnirmano.mentalwellness;

public class ModalForBookedSession {
    private String time,date,link,upcoming;

    public ModalForBookedSession(String time, String date, String link, String upcoming) {
        this.time = time;
        this.date = date;
        this.link = link;
        this.upcoming = upcoming;
    }

    public ModalForBookedSession() {
    }

    public String getUpcoming() {
        return upcoming;
    }

    public void setUpcoming(String upcoming) {
        this.upcoming = upcoming;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
