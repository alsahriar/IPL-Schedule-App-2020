package com.softtechbd.iplschedule2020.Model;

public class VenuesModel {
    private int venueImg;
    private String venueName,veneuHometeam,venueLocation,venueOpened,venueCapacity;

    public VenuesModel() {
    }

    public VenuesModel(int venueImg, String venueName, String veneuHometeam, String venueLocation, String venueOpened, String venueCapacity) {
        this.venueImg = venueImg;
        this.venueName = venueName;
        this.veneuHometeam = veneuHometeam;
        this.venueLocation = venueLocation;
        this.venueOpened = venueOpened;
        this.venueCapacity = venueCapacity;
    }

    public int getVenueImg() {
        return venueImg;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getVeneuHometeam() {
        return veneuHometeam;
    }

    public String getVenueLocation() {
        return venueLocation;
    }

    public String getVenueOpened() {
        return venueOpened;
    }

    public String getVenueCapacity() {
        return venueCapacity;
    }

    public void setVenueImg(int venueImg) {
        this.venueImg = venueImg;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public void setVeneuHometeam(String veneuHometeam) {
        this.veneuHometeam = veneuHometeam;
    }

    public void setVenueLocation(String venueLocation) {
        this.venueLocation = venueLocation;
    }

    public void setVenueOpened(String venueOpened) {
        this.venueOpened = venueOpened;
    }

    public void setVenueCapacity(String venueCapacity) {
        this.venueCapacity = venueCapacity;
    }
}
