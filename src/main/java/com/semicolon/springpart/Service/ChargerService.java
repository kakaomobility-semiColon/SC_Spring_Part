package com.semicolon.springpart.Service;

import com.semicolon.springpart.Repository.ChargerRepository;
import com.semicolon.springpart.entity.ChargerApiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChargerService {

    private final ChargerRepository chargerRepository;

    @Autowired
    public ChargerService(ChargerRepository chargerRepository) {
        this.chargerRepository = chargerRepository;
    }

    public List<ChargerApiEntity> getAllChargers() {
        return chargerRepository.findAll();
    }
    public List<ChargerApiEntity> searchChargersInArea(float swLat, float swLng, float neLat, float neLng) {
        // 북동쪽, 남서쪽 좌표를 기준으로 주변의 충전기 조회
        return chargerRepository.findByLocationWithin(swLat, swLng, neLat, neLng);
    }

        // id 기준 상세 정보 조회
    public ChargerApiEntity getChargerDetailById(String stationChargerId) {
        return chargerRepository.findByStationChargerId(stationChargerId);
    }
}