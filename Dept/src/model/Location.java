package model;

public class Location {
    private String locationID;
    private String locationName;
    private String locationCountry;

    public Location(String locationID, String locationName, String locationCountry) {
        this.locationID = locationID;
        this.locationName = locationName;
        this.locationCountry = locationCountry;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public void setLocationCountry(String locationCountry) {
        this.locationCountry = locationCountry;
    }
}
