package com.semicolon.springpart.dto;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class ChargerDetailDTO {
    private String stationChargerId;
    private String name;
    private String chargerType;
    private String chargerTypeName;
    private String address;
    private Float lat;
    private Float lng;
    private String operatorId;
    private String operatorName;
    private Integer output;
    private String kindDetail;
    private String kindDetailName;
    private Date updatedAt;

    public ChargerDetailDTO(String stationChargerId, String name, String chargerType, String address, Float lat, Float lng, String operatorId, String operatorName, Integer output, String kindDetail, Date updatedAt) {
        this.stationChargerId = stationChargerId;
        this.name = name;
        this.chargerType = chargerType;
        this.chargerTypeName = mapChargerTypeToName(chargerType);
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.output = output;
        this.kindDetail = kindDetail;
        this.kindDetailName = mapKindDetailToName(kindDetail);
        this.updatedAt = updatedAt;
    }

    private String mapChargerTypeToName(String chargerType) {
        switch (chargerType) {
            case "01": return "DC 차데모";
            case "02": return "AC 완속";
            case "03": return "DC 차데모+AC3 상";
            case "04": return "DC 콤보";
            case "05": return "DC 차데모+DC 콤보";
            case "06": return "DC 차데모+AC3 상+DC 콤보";
            case "07": return "AC3 상";
            case "08": return "DC 콤보(완속)";
            case "89": return "H2";
            default: return "알 수 없음";
        }
    }

    private String mapKindDetailToName(String kindDetail) {
        Map<String, String> kindDetailMap = new HashMap<>();
        kindDetailMap.put("A001", "관공서");
        kindDetailMap.put("A002", "주민센터");
        kindDetailMap.put("A003", "공공기관");
        kindDetailMap.put("A004", "지자체시설");
        kindDetailMap.put("B001", "공영주차장");
        kindDetailMap.put("B002", "공원주차장");
        kindDetailMap.put("B003", "환승주차장");
        kindDetailMap.put("B004", "일반주차장");
        kindDetailMap.put("C001", "고속도로 휴게소");
        kindDetailMap.put("C002", "지방도로 휴게소");
        kindDetailMap.put("C003", "쉼터");
        kindDetailMap.put("D001", "공원");
        kindDetailMap.put("D002", "전시관");
        kindDetailMap.put("D003", "민속마을");
        kindDetailMap.put("D004", "생태공원");
        kindDetailMap.put("D005", "홍보관");
        kindDetailMap.put("D006", "관광안내소");
        kindDetailMap.put("D007", "관광지");
        kindDetailMap.put("D008", "박물관");
        kindDetailMap.put("D009", "유적지");
        kindDetailMap.put("E001", "마트(쇼핑몰)");
        kindDetailMap.put("E002", "백화점");
        kindDetailMap.put("E003", "숙박시설");
        kindDetailMap.put("E004", "골프장(CC)");
        kindDetailMap.put("E005", "카페");
        kindDetailMap.put("E006", "음식점");
        kindDetailMap.put("E007", "주유소");
        kindDetailMap.put("E008", "영화관");
        kindDetailMap.put("F001", "서비스센터");
        kindDetailMap.put("F002", "정비소");
        kindDetailMap.put("G001", "군부대");
        kindDetailMap.put("G002", "야영장");
        kindDetailMap.put("G003", "공중전화부스");
        kindDetailMap.put("G004", "기타");
        kindDetailMap.put("G005", "오피스텔");
        kindDetailMap.put("G006", "단독주택");
        kindDetailMap.put("H001", "아파트");
        kindDetailMap.put("H002", "빌라");
        kindDetailMap.put("H003", "사업장(사옥)");
        kindDetailMap.put("H004", "기숙사");
        kindDetailMap.put("H005", "연립주택");
        kindDetailMap.put("I001", "병원");
        kindDetailMap.put("I002", "종교시설");
        kindDetailMap.put("I003", "보건소");
        kindDetailMap.put("I004", "경찰서");
        kindDetailMap.put("I005", "도서관");
        kindDetailMap.put("I006", "복지관");
        kindDetailMap.put("I007", "수련원");
        kindDetailMap.put("I008", "금융기관");
        kindDetailMap.put("J001", "학교");
        kindDetailMap.put("J002", "교육원");
        kindDetailMap.put("J003", "학원");
        kindDetailMap.put("J004", "공연장");
        kindDetailMap.put("J005", "관람장");
        kindDetailMap.put("J006", "동식물원");
        kindDetailMap.put("J007", "경기장");

        return kindDetailMap.getOrDefault(kindDetail, "알 수 없음");
    }


    public String getStationChargerId() {
        return stationChargerId;
    }

    public String getName() {
        return name;
    }

    private String getChargerType() {
        return chargerType;
    }

    public void setChargerType(String chargerType) {
        this.chargerType = chargerType;
        this.chargerTypeName = mapChargerTypeToName(chargerType);
    }

    public String getChargerTypeName() {
        return chargerTypeName;
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

    private String getOperatorId() {
        return operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public String getOutput() {
        return output + "kW";
    }

    private String getKindDetail() {
        return kindDetail;
    }

    public String getKindDetailName() {
        return kindDetailName;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
