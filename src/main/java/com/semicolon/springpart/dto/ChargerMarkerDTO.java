package com.semicolon.springpart.dto;

public class ChargerMarkerDTO {
    private final String name;
    private final float lat;
    private final float lng;
    private final String address;
    private final String operatorName;
    private final Integer output;

    public ChargerMarkerDTO(String name, float lat, float lng, String address, String operatorName, Integer output) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.operatorName = operatorName;
        this.output = output;
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

    public String getOperatorName() {
        return operatorName;
    }
    public String getOutput() {
        return output + "kW";
    }
}


