package com.ansrnirmano.mentalwellness;

public class TempModal {
    String label,title1,title2,content1,content2,duration;

    public TempModal() {
    }

    public TempModal(String label, String title1, String title2, String content1, String content2, String duration) {
        this.label = label;
        this.title1 = title1;
        this.title2 = title2;
        this.content1 = content1;
        this.content2 = content2;
        this.duration = duration;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
