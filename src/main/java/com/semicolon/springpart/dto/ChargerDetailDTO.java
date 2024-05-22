package com.semicolon.springpart.dto;

import java.sql.Date;

public class ChargerDetailDTO {
    private String stationChargerId;
    private String name;
    private String chargerType;
    private String address;
    private Float lat;
    private Float lng;
    private String operatorId;
    private String operatorName;
    private Integer output;
    private String kindDetail;
    private Date updatedAt;

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

    public void setStationChargerId(String stationChargerId) {
        this.stationChargerId = stationChargerId;
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
