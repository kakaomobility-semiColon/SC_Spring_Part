package com.semicolon.springpart.service;


import com.semicolon.springpart.CustomCoordinateCache;
import com.semicolon.springpart.repository.ChargerRepository;
import com.semicolon.springpart.dto.ChargerDetailDTO;
import com.semicolon.springpart.dto.ChargerMarkerDTO;
import com.semicolon.springpart.dto.ChargerSearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class ChargerService {

    private final ChargerRepository chargerRepository;
    private final CustomCoordinateCache coordinateCache;
    private static final Logger logger = LoggerFactory.getLogger(ChargerService.class);


    @Autowired
    public ChargerService(ChargerRepository chargerRepository, CustomCoordinateCache coordinateCache) {
        this.chargerRepository = chargerRepository;
        this.coordinateCache = coordinateCache;
    }

    @Cacheable(value = "chargersByNameOrAddress", key = "#keyword")
    public List<ChargerSearchDTO> searchChargersDTOByNameOrAddress(String keyword) {
        if (!StringUtils.hasText(keyword)) {
            return Collections.emptyList(); // 키워드가 비어있는 경우 빈 리스트 반환
        }
        logger.info("Object with id {} retrieved from cache or generated.", keyword);
        List<ChargerSearchDTO> chargers = chargerRepository.searchChargersByNameOrAddress(keyword);
        return chargers;
    }

    public List<ChargerMarkerDTO> searchChargersInArea(float swLat, float swLng, float neLat, float neLng) {
        if (Float.isNaN(swLat) || Float.isNaN(swLng) || Float.isNaN(neLat) || Float.isNaN(neLng)) {
            throw new IllegalArgumentException("Invalid coordinate values: NaN");
        }

        String key = swLat + "," + swLng + "," + neLat + "," + neLng;
        List<ChargerMarkerDTO> cachedCharger = (List<ChargerMarkerDTO>) coordinateCache.get(key);
        if (cachedCharger != null) {
            logger.info("Cache hit for coordinates: {}", key);
            return cachedCharger;
        }

        logger.info("Cache miss for coordinates: {}", key);
        List<ChargerMarkerDTO> charger = chargerRepository.findByLocationWithin(swLat, swLng, neLat, neLng);
        coordinateCache.put(key, charger);
        return charger;
    }

    // id 기준 상세 정보 조회
    // id 기준 상세 정보 조회
    public ChargerDetailDTO getChargerDetailById(String stationChargerId) {
        return chargerRepository.findByStationChargerId(stationChargerId);
    }

    public List<ChargerMarkerDTO> getAllChargers() {
        List<ChargerMarkerDTO> all = chargerRepository.findAllChargerMarkers();
        return all;
    }
}