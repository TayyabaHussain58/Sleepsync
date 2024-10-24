package com.example.sleepapp;

public class ListItem {
    private int imageResId;
    private String title;
    private String subtitle;
    private String link;

    public ListItem( String title, String subtitle, String link) {
        this.title = title;
        this.subtitle = subtitle;
        this.link = link;
    }



    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getLink() {
        return link;
    }
}
