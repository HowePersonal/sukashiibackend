package com.example.sukashii.model;

import org.springframework.data.annotation.Id;

public class Anime {

    @Id
    private long id;

    private String title;

    private String med_picture;

    private String large_picture;

    private String start_date;

    private String end_date;

    private String description;

    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getNum_episodes() {
        return num_episodes;
    }

    public void setNum_episodes(int num_episodes) {
        this.num_episodes = num_episodes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getLarge_picture() {
        return large_picture;
    }

    public void setLarge_picture(String large_picture) {
        this.large_picture = large_picture;
    }

    public String getMed_picture() {
        return med_picture;
    }

    public void setMed_picture(String med_picture) {
        this.med_picture = med_picture;
    }

    private int num_episodes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
