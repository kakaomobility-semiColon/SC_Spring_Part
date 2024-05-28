package com.semicolon.springpart.repository;

import com.semicolon.springpart.dto.ChargerDetailDTO;
import com.semicolon.springpart.dto.ChargerMarkerDTO;
import com.semicolon.springpart.dto.ChargerSearchDTO;
import com.semicolon.springpart.entity.ChargerApiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargerRepository extends JpaRepository<ChargerApiEntity, Long> {


    @Query("SELECT new com.semicolon.springpart.dto.ChargerMarkerDTO(c.name, c.lat, c.lng, c.address,c.operatorName,c.output) FROM ChargerApiEntity c")
    List<ChargerMarkerDTO> findAllChargerMarkers();

    @Query("SELECT new com.semicolon.springpart.dto.ChargerSearchDTO(c.stationChargerId, c.name, c.chargerType, c.address, c.operatorName, c.output, c.kindDetail) FROM ChargerApiEntity c WHERE c.name LIKE %:keyword% OR c.address LIKE %:keyword%")
    List<ChargerSearchDTO> searchChargersByNameOrAddress(@Param("keyword") String keyword);

    @Query("SELECT new com.semicolon.springpart.dto.ChargerMarkerDTO(c.name, c.lat, c.lng, c.address, c.operatorName, c.output) FROM ChargerApiEntity c WHERE c.lat BETWEEN :swLat AND :neLat AND c.lng BETWEEN :swLng AND :neLng")
    List<ChargerMarkerDTO> findByLocationWithin(@Param("swLat") float swLat,
                                                      @Param("swLng") float swLng,
                                                      @Param("neLat") float neLat,
                                                      @Param("neLng") float neLng);

    @Query("SELECT new com.semicolon.springpart.dto.ChargerDetailDTO(c.stationChargerId, c.name, c.chargerType, c.address, c.lat, c.lng, c.operatorId, c.operatorName, c.output, c.kindDetail, c.updatedAt) FROM ChargerApiEntity c WHERE c.stationChargerId = :stationChargerId")
    ChargerDetailDTO findByStationChargerId(@Param("stationChargerId") String stationChargerId);
}