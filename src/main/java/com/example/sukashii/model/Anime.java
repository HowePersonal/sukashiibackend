package com.example.sukashii.model;

import org.springframework.data.annotation.Id;
import java.sql.Date;

public class Anime {

    @Id
    private long id;

    private String title;

    private int numEpisodes;

    private String medPicture;

    private String largePicture;

    private Date startDate;

    private Date endDate;

    private String description;

    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getNumEpisodes() {
        return numEpisodes;
    }

    public void setNumEpisodes(int numEpisodes) {
        this.numEpisodes = numEpisodes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getLarge_picture() {
        return largePicture;
    }

    public void setLargePicture(String largePicture) {
        this.largePicture = largePicture;
    }

    public String getMedPicture() {
        return medPicture;
    }

    public void setMedPicture(String medPicture) {
        this.medPicture = medPicture;
    }

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
