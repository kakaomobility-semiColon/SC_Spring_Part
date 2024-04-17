package com.semicolon.springpart.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "charger")
public class ChargerApiEntity {

    @Id
    @Column(name = "station_charger_id")
    private String stationChargerId;

    @Column(name = "name")
    private String name;

    @Column(name = "charger_type")
    private String chargerType;

    @Column(name = "address")
    private String address;

    @Column(name = "lat")
    private Float lat;

    @Column(name = "lng")
    private Float lng;

    @Column(name = "operator_id")
    private String operatorId;

    @Column(name = "operator_name")
    private String operatorName;

    @Column(name = "output")
    private Integer output;

    @Column(name = "zscode")
    private String zscode;

    @Column(name = "kind_detail")
    private String kindDetail;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "apt_code")
    private String aptCode;

    // Getters and setters

    public String getStationChargerId() {
        return stationChargerId;
    }

    public void setStationChargerId(String stationChargerId) {
        this.stationChargerId = stationChargerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChargerType() {
        return chargerType;
    }

    public void setChargerType(String chargerType) {
        this.chargerType = chargerType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getOutput() {
        return output;
    }

    public void setOutput(Integer output) {
        this.output = output;
    }

    public String getZscode() {
        return zscode;
    }

    public void setZscode(String zscode) {
        this.zscode = zscode;
    }

    public String getKindDetail() {
        return kindDetail;
    }

    public void setKindDetail(String kindDetail) {
        this.kindDetail = kindDetail;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAptCode() {
        return aptCode;
    }

    public void setAptCode(String aptCode) {
        this.aptCode = aptCode;
    }
}
