package com.example.user.eventcalendar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 20.01.2017.
 */

public class EventModel {


    @SerializedName("EventId")
    @Expose
    private String eventId;
    @SerializedName("EventDate")
    @Expose
    private String eventDate;
    @SerializedName("EventTime")
    @Expose
    private String eventTime;
    @SerializedName("ArtistID")
    @Expose
    private String artistID;
    @SerializedName("ArtistName")
    @Expose
    private String artistName;
    @SerializedName("ArtistImage")
    @Expose
    private String artistImage;
    @SerializedName("ArtiestPopularity")
    @Expose
    private String artiestPopularity;
    @SerializedName("ArtistTourName")
    @Expose
    private String artistTourName;
    @SerializedName("VenueID")
    @Expose
    private String venueID;
    @SerializedName("VenueName")
    @Expose
    private String venueName;
    @SerializedName("VenueCity")
    @Expose
    private String venueCity;
    @SerializedName("VenueCountry")
    @Expose
    private String venueCountry;
    @SerializedName("VenueZipcode")
    @Expose
    private String venueZipcode;
    @SerializedName("VenueStreet")
    @Expose
    private String venueStreet;
    @SerializedName("VenuebuildingNumber")
    @Expose
    private String venuebuildingNumber;
    @SerializedName("VenueLat")
    @Expose
    private String venueLat;
    @SerializedName("VanueLong")
    @Expose
    private String vanueLong;
    @SerializedName("VenueImageUrl")
    @Expose
    private String venueImageUrl;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getArtistID() {
        return artistID;
    }

    public void setArtistID(String artistID) {
        this.artistID = artistID;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistImage() {
        return artistImage;
    }

    public void setArtistImage(String artistImage) {
        this.artistImage = artistImage;
    }

    public String getArtiestPopularity() {
        return artiestPopularity;
    }

    public void setArtiestPopularity(String artiestPopularity) {
        this.artiestPopularity = artiestPopularity;
    }

    public String getArtistTourName() {
        return artistTourName;
    }

    public void setArtistTourName(String artistTourName) {
        this.artistTourName = artistTourName;
    }

    public String getVenueID() {
        return venueID;
    }

    public void setVenueID(String venueID) {
        this.venueID = venueID;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueCity() {
        return venueCity;
    }

    public void setVenueCity(String venueCity) {
        this.venueCity = venueCity;
    }

    public String getVenueCountry() {
        return venueCountry;
    }

    public void setVenueCountry(String venueCountry) {
        this.venueCountry = venueCountry;
    }

    public String getVenueZipcode() {
        return venueZipcode;
    }

    public void setVenueZipcode(String venueZipcode) {
        this.venueZipcode = venueZipcode;
    }

    public String getVenueStreet() {
        return venueStreet;
    }

    public void setVenueStreet(String venueStreet) {
        this.venueStreet = venueStreet;
    }

    public String getVenuebuildingNumber() {
        return venuebuildingNumber;
    }

    public void setVenuebuildingNumber(String venuebuildingNumber) {
        this.venuebuildingNumber = venuebuildingNumber;
    }

    public String getVenueLat() {
        return venueLat;
    }

    public void setVenueLat(String venueLat) {
        this.venueLat = venueLat;
    }

    public String getVanueLong() {
        return vanueLong;
    }

    public void setVanueLong(String vanueLong) {
        this.vanueLong = vanueLong;
    }

    public String getVenueImageUrl() {
        return venueImageUrl;
    }

    public void setVenueImageUrl(String venueImageUrl) {
        this.venueImageUrl = venueImageUrl;
    }
}
