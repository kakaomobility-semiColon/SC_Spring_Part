package com.semicolon.springpart.Service;

import com.semicolon.springpart.Repository.ChargerRepository;
import com.semicolon.springpart.dto.ChargerDetailDTO;
import com.semicolon.springpart.dto.ChargerMarkerDTO;
import com.semicolon.springpart.dto.ChargerSearchDTO;
import com.semicolon.springpart.entity.ChargerApiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChargerService {

    private final ChargerRepository chargerRepository;


    @Autowired
    public ChargerService(ChargerRepository chargerRepository) {
        this.chargerRepository = chargerRepository;
    }

    public List<ChargerSearchDTO> searchChargersDTOByNameOrAddress(String keyword) {
        List<ChargerSearchDTO> chargers = chargerRepository.searchChargersByNameOrAddress(keyword);
        return chargers;
    }

    @Cacheable(value = "searchChargersInAreaCache", key = "#swLat + ',' + #swLng + ',' + #neLat + ',' + #neLng")
    public List<ChargerMarkerDTO> searchChargersInArea(float swLat, float swLng, float neLat, float neLng) {
        // 북동쪽, 남서쪽 좌표를 기준으로 주변의 충전기 조회
        List<ChargerMarkerDTO> charger = chargerRepository.findByLocationWithin(swLat, swLng, neLat, neLng);
        return charger;
    }

    // id 기준 상세 정보 조회
    public ChargerDetailDTO getChargerDetailById(String stationChargerId) {
        return chargerRepository.findByStationChargerId(stationChargerId);
    }
    public List<ChargerApiEntity> searchChargersByNameOrAddress(String keyword) {
        //이름이나 주소로 충전기 조회
        return chargerRepository.searchChargersByNameOrAddress(keyword);
    }
}