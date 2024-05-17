package com.semicolon.springpart.dto;

public class ChargerSearchDTO {
    private final String name;
    private final String chargerType;
    private final String address;
    private final String operatorName;
    private final Integer output;
    private final String kindDetail;

    public ChargerSearchDTO(String name, String chargerType, String address, String operatorName, Integer output, String kindDetail) {
        this.name = name;
        this.chargerType = chargerType;
        this.address = address;
        this.operatorName = operatorName;
        this.output = output;
        this.kindDetail = kindDetail;
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

    public String getOperatorName() {
        return operatorName;
    }

    public Integer getOutput() {
        return output;
    }

    public String getKindDetail() {
        return kindDetail;
    }
}
