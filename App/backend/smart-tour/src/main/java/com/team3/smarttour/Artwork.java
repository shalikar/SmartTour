package com.team3.smarttour;

import org.apache.jena.rdf.model.Literal;

public class Artwork {

   private String artworkID;
   private String title;
   private String text;
    private String image;
    private String height;
    private String width;
    private String artistName;
    private String artistID;
    private String medium;
    private String type;
    private String year;
    private String artistImg;
    private String artistBirthDate;
    private String artistDeathDate;


    public String getArtworkId() {
        return artworkID;
    }

    public void setArtworkId(String artworkId) {
        this.artworkID = artworkId;
    }

    public String getTitle() {
        return title;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getArtistBirthDate() {
        return artistBirthDate;
    }

    public void setArtistBirthDate(String artistBirthDate) {
        this.artistBirthDate = artistBirthDate;
    }

    public String getArtistDeathDate() {
        return artistDeathDate;
    }

    public void setArtistDeathDate(String artistDeathDate) {
        this.artistDeathDate = artistDeathDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getArtistImg() {
        return artistImg;
    }

    public void setArtistImg(String artistImg) {
        this.artistImg = artistImg;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistId(String artistID) {
        this.artistID = artistID;
    }
}
