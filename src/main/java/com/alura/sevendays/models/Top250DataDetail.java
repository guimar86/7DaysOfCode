package com.alura.sevendays.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Top250DataDetail
{
    private String Id ;
    private String Rank ;
    private String Title ;
    private String FullTitle ;
    private String Year ;
    private String Image ;
    private String Crew ;
    @JsonProperty("imDbRating")
    private String imDbRating ;
    @JsonProperty("imDbRatingCount")
    private String IMDbRatingCount ;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getFullTitle() {
        return FullTitle;
    }

    public void setFullTitle(String fullTitle) {
        FullTitle = fullTitle;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getCrew() {
        return Crew;
    }

    public void setCrew(String crew) {
        Crew = crew;
    }

    public String getIMDbRating() {
        return imDbRating;
    }

    public void setIMDbRating(String IMDbRating) {
        this.imDbRating = IMDbRating;
    }

    public String getIMDbRatingCount() {
        return IMDbRatingCount;
    }

    public void setIMDbRatingCount(String IMDbRatingCount) {
        this.IMDbRatingCount = IMDbRatingCount;
    }
}
