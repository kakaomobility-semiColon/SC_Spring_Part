package com.semicolon.springpart.controller;

import com.semicolon.springpart.service.BookmarkService;
import com.semicolon.springpart.service.ChargerService;
import com.semicolon.springpart.dto.ChargerDetailDTO;
import com.semicolon.springpart.dto.ChargerMarkerDTO;
import com.semicolon.springpart.dto.ChargerSearchDTO;
import com.semicolon.springpart.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/charger")
@Tag(name = "Charger", description = "Charger management APIs")
public class ChargerController {

    private final ChargerService chargerService;

    public ChargerController(ChargerService chargerService, BookmarkService bookmarkService) {
        this.chargerService = chargerService;
    }

    @CrossOrigin("*")
    @GetMapping("/search")
    @Operation(summary = "이름이나 주소로 충전소 검색", description = "이름이나 주소에 따른 충전소 리스트 반환")
    public ResponseEntity<Response<List<ChargerSearchDTO>>> searchChargersByNameOrAddress(
            @RequestParam(value = "keyword") String keyword) {
        List<ChargerSearchDTO> chargers = chargerService.searchChargersDTOByNameOrAddress(keyword);
        return createResponse(chargers);
    }

    @CrossOrigin("*")
    @GetMapping("/search/marker")
    @Operation(summary = "주변 충전소 표시", description = "지도에서 보고 있는 사각 영역의 충전소 리스트 반환")
    public ResponseEntity<Response<List<ChargerMarkerDTO>>> searchChargersNearby(
            @RequestParam float swLat, @RequestParam float swLng,
            @RequestParam float neLat, @RequestParam float neLng) {
        List<ChargerMarkerDTO> charger = chargerService.searchChargersInArea(swLat, swLng, neLat, neLng);
        return createResponse(charger);
    }

    @CrossOrigin("*")
    @GetMapping("/{chargerId}/detail")
    @Operation(summary = "충전소 상세정보", description = "충전소 ID를 기준으로 상세정보 반환")
    public ResponseEntity<Response<ChargerDetailDTO>> getChargerDetail(@PathVariable String chargerId) {
        ChargerDetailDTO detail = chargerService.getChargerDetailById(chargerId);
        return createResponse(detail);
    }

    @CrossOrigin("*")
    @GetMapping("/all")
    @Operation(summary = "모든 충전소 가져오기(사용X)", description = "모든 충전소 리스트 반환")
    public ResponseEntity<Response<List<ChargerMarkerDTO>>> getAllChargers() {
        List<ChargerMarkerDTO> all = chargerService.getAllChargers();
        return createResponse(all);
    }

    private <T> ResponseEntity<Response<T>> createResponse(T data) {
        Response<T> response = new Response<>(data);
        return ResponseEntity.ok(response);
    }

    private ResponseEntity<Response<Void>> createErrorResponse(HttpStatus status, String message) {
        Response<Void> response = new Response<>(status, message);
        return ResponseEntity.status(status).body(response);
    }
}