package com.Employee.employeeDBWithJPA.model;

public class LocationModel {

    private String locationName;
    private String locationCountry;
//
//    public LocationModel( String locationName, String locationCountry) {
//
//        this.locationName = locationName;
//        this.locationCountry = locationCountry;
//    }
//


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
