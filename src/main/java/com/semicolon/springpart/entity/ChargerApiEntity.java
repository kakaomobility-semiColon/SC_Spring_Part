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


    public String getZscode() {
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

}

