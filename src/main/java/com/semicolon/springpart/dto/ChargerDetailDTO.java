package com.semicolon.springpart.dto;

import java.sql.Date;

public class ChargerDetailDTO {
    private final String stationChargerId;
    private final String name;
    private final String chargerType;
    private final String address;
    private final Float lat;
    private final Float lng;
    private final String operatorId;
    private final String operatorName;
    private final Integer output;
    private final String kindDetail;
    private final Date updatedAt;
    public ChargerDetailDTO(String stationChargerId, String name, String chargerType, String address, Float lat, Float lng, String operatorId, String operatorName, Integer output, String kindDetail, Date updatedAt) {
        this.stationChargerId = stationChargerId;
        this.name = name;
        this.chargerType = chargerType;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.output = output;
        this.kindDetail = kindDetail;
        this.updatedAt = updatedAt;
    }

    public String getStationChargerId() {
        return stationChargerId;
    }

    public String getName() {
        return name;
    }

    public String getChargerType() {
        return chargerType;
    }

    public String getAddress() {
        return address;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLng() {
        return lng;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public Integer getOutput() {
        return output;
    }

    public String getKindDetail() {
        return kindDetail;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
