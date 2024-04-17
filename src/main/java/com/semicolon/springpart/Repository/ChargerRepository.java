package com.semicolon.springpart.Repository;

import com.semicolon.springpart.entity.ChargerApiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargerRepository extends JpaRepository<ChargerApiEntity, String> {
    Page<ChargerApiEntity> findAll(Pageable pageable);
}
