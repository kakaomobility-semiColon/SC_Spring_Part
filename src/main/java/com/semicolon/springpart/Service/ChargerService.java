package com.semicolon.springpart.Service;

import com.semicolon.springpart.Repository.ChargerRepository;
import com.semicolon.springpart.entity.ChargerApiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    public List<ChargerApiEntity> searchChargersByNameOrAddress(String keyword) {
        //이름이나 주소로 충전기 조회
        return chargerRepository.searchChargersByNameOrAddress(keyword);
    }

    // 이 메서드에 캐시 적용, key를 lat, lng로 지정
    @Cacheable(value = "searchChargersInAreaCache", key = "#swLat + ',' + #swLng + ',' + #neLat + ',' + #neLng")
    public List<ChargerApiEntity> searchChargersInArea(float swLat, float swLng, float neLat, float neLng) {
        // 북동쪽, 남서쪽 좌표를 기준으로 주변의 충전기 조회
        List<ChargerApiEntity> chargers = chargerRepository.findByLocationWithin(swLat, swLng, neLat, neLng);
        return chargers;
    }

        // id 기준 상세 정보 조회
    public ChargerApiEntity getChargerDetailById(String chargerId) {
        return chargerRepository.findByStationChargerId(chargerId);
    }
}