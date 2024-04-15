package com.semicolon.springpart.Repository;

import com.semicolon.springpart.entity.ChargerApiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargerRepository extends JpaRepository<ChargerApiEntity, String> {
    List<ChargerApiEntity> findAll();
}
