package com.semicolon.springpart.Repository;

import com.semicolon.springpart.entity.ChargerApiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargerRepository extends JpaRepository<ChargerApiEntity, Long> {
    @Query("SELECT c FROM ChargerApiEntity c WHERE c.lat BETWEEN :swLat AND :neLat AND c.lng BETWEEN :swLng AND :neLng")
    List<ChargerApiEntity> findByLocationWithin(@Param("swLat") float swLat,
                                                @Param("swLng") float swLng,
                                                @Param("neLat") float neLat,
                                                @Param("neLng") float neLng);
    @Query("SELECT c FROM ChargerApiEntity c WHERE c.stationChargerId = :stationChargerId")
    ChargerApiEntity findByStationChargerId(@Param("stationChargerId") String stationChargerId);
}