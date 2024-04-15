package com.semicolon.springpart.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "charger_ver1")
public class ChargerApiEntity {

    @Id
    private String stationChargerId;

    private String name;
    private String chargerType;
    private String address;
    private double lat;
    private double lng;
    private String operatorId;
    private String operatorName;
    private int output;
    private int zscode;
    private String kindDetail;

    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    private String aptCode;

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

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public int getOutput() {
        return output;
    }

    public int getZscode() {
        return zscode;
    }

    public String getKindDetail() {
        return kindDetail;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getAptCode() {
        return aptCode;
    }

    // Setter 메서드들
    public void setStationChargerId(String stationChargerId) {
        this.stationChargerId = stationChargerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChargerType(String chargerType) {
        this.chargerType = chargerType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public void setZscode(int zscode) {
        this.zscode = zscode;
    }

    public void setKindDetail(String kindDetail) {
        this.kindDetail = kindDetail;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAptCode(String aptCode) {
        this.aptCode = aptCode;
    }
}