package com.semicolon.springpart.dto;

public class ChargerMarkerDTO {
    private final String name;
    private final float lat;
    private final float lng;
    private final String address;

    public ChargerMarkerDTO(String name, float lat, float lng, String address) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public float getLat() {
        return lat;
    }
    public float getLng() {
        return lng;
    }

    public String getAddress(){
        return address;
    }
}


