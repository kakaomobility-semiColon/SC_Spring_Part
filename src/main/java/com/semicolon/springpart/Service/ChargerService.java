package com.semicolon.springpart.Service;


import com.semicolon.springpart.CacheType;
import com.semicolon.springpart.Repository.ChargerRepository;
import com.semicolon.springpart.dto.ChargerDetailDTO;
import com.semicolon.springpart.dto.ChargerMarkerDTO;
import com.semicolon.springpart.dto.ChargerSearchDTO;
import com.semicolon.springpart.entity.ChargerApiEntity;
import com.semicolon.springpart.util.AESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChargerService {

    private final ChargerRepository chargerRepository;
    private static final Logger logger = LoggerFactory.getLogger(ChargerService.class);


    @Autowired
    public ChargerService(ChargerRepository chargerRepository) {
        this.chargerRepository = chargerRepository;
    }

    @Cacheable(value = "chargersByNameOrAddress", key = "#keyword")
    public List<ChargerSearchDTO> searchChargersDTOByNameOrAddress(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return Collections.emptyList(); // 키워드가 비어있는 경우 빈 리스트 반환
        }
        logger.info("Object with id {} retrieved from cache or generated.", keyword);
        List<ChargerSearchDTO> chargers = chargerRepository.searchChargersByNameOrAddress(keyword);
        return chargers;
    }

    @Cacheable(value = "chargersInArea", key = "#swLat + ',' + #swLng + ',' + #neLat + ',' + #neLng")
    public List<ChargerMarkerDTO> searchChargersInArea(float swLat, float swLng, float neLat, float neLng) {
        if (Float.isNaN(swLat) || Float.isNaN(swLng) || Float.isNaN(neLat) || Float.isNaN(neLng)) {
            throw new IllegalArgumentException("Invalid coordinate values: NaN");
        }
        logger.info("Object with latitudes {} - {} and longitudes {} - {} retrieved from cache or generated.", swLat, neLat, swLng, neLng);
        // 북동쪽, 남서쪽 좌표를 기준으로 주변의 충전기 조회
        List<ChargerMarkerDTO> charger = chargerRepository.findByLocationWithin(swLat, swLng, neLat, neLng);
        return charger;
    }

    // id 기준 상세 정보 조회
    public ChargerDetailDTO getChargerDetailById(String stationChargerId) {
        ChargerDetailDTO detail = chargerRepository.findByStationChargerId(stationChargerId);
        try {
            String encryptedId = AESUtil.encrypt(detail.getStationChargerId());
            detail.setStationChargerId(encryptedId);  // 암호화된 ID로 설정
        } catch (Exception e) {
            e.printStackTrace();  // 예외 처리는 실제 상황에 맞게 조정하세요.
        }
        return detail;
    }

    public List<ChargerMarkerDTO> getAllChargers() {
        List<ChargerMarkerDTO> all = chargerRepository.findAllChargerMarkers();
        return all;
    }
}