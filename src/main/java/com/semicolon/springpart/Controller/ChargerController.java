package com.semicolon.springpart.Controller;

import com.semicolon.springpart.Service.BookmarkService;
import com.semicolon.springpart.Service.ChargerService;
import com.semicolon.springpart.entity.BookmarkEntity;
import com.semicolon.springpart.entity.ChargerApiEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/charger")
public class ChargerController {

    private final ChargerService chargerService;
    private final BookmarkService bookmarkService;

    public ChargerController(ChargerService chargerService, BookmarkService bookmarkService) {
        this.chargerService = chargerService;
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/{chargerId}/bookmark")
    public ResponseEntity<Map<String, Object>> addBookmark(@RequestParam String userId, @PathVariable String chargerId) {
        BookmarkEntity bookmark = bookmarkService.addBookmark(userId, chargerId);
        return createResponse(bookmark);
    }

    @GetMapping("/bookmarks")
    public ResponseEntity<Map<String, Object>> getBookmarks(@RequestParam String userId) {
        List<BookmarkEntity> bookmarks = bookmarkService.getBookmarksByUserId(userId);
        return createResponse(bookmarks);
    }

    @DeleteMapping("/{chargerId}/bookmark")
    public ResponseEntity<Map<String, Object>> removeBookmark(@RequestParam String userId, @PathVariable String chargerId) {
        bookmarkService.removeBookmark(userId, chargerId);
        return createResponse(null);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchChargersByNameOrAddress(
            @RequestParam(value = "keyword") String keyword) {
        List<ChargerApiEntity> charger = chargerService.searchChargersByNameOrAddress(keyword);
        return createResponse(charger);
    }

    @GetMapping("/search/marker")
    public ResponseEntity<Map<String, Object>> searchChargersNearby(@RequestParam float swLat, @RequestParam float swLng,
                                                                    @RequestParam float neLat, @RequestParam float neLng) {
        List<ChargerApiEntity> charger = chargerService.searchChargersInArea(swLat, swLng, neLat, neLng);
        return createResponse(charger);
    }

    @GetMapping("/{chargerId}/detail")
    public ResponseEntity<Map<String, Object>> getChargerDetail(@PathVariable String chargerId) {
        ChargerApiEntity charger = chargerService.getChargerDetailById(chargerId);
        return createResponse(charger);
    }

    private ResponseEntity<Map<String, Object>> createResponse(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("message", "요청이 성공했습니다.");
        response.put("result", data);
        response.put("success", true);
        return ResponseEntity.ok().body(response);
    }


}