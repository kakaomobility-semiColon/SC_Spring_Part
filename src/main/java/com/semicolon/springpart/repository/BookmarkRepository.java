package com.semicolon.springpart.repository;

import com.semicolon.springpart.entity.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {
    List<BookmarkEntity> findByUserId(String userId);
    BookmarkEntity findByUserIdAndChargerId(String userId, String chargerId);
    void deleteByUserIdAndChargerId(String userId, String chargerId);
}
