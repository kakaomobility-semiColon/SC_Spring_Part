package com.semicolon.springpart.controller;

import com.semicolon.springpart.service.BookmarkService;
import com.semicolon.springpart.service.ChargerService;
import com.semicolon.springpart.dto.ChargerDetailDTO;
import com.semicolon.springpart.dto.ChargerMarkerDTO;
import com.semicolon.springpart.dto.ChargerSearchDTO;
import com.semicolon.springpart.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/charger")
public class ChargerController {

    private final ChargerService chargerService;

    public ChargerController(ChargerService chargerService, BookmarkService bookmarkService) {
        this.chargerService = chargerService;
    }

    @CrossOrigin("*")
    @GetMapping("/search")
    public ResponseEntity<Response<List<ChargerSearchDTO>>> searchChargersByNameOrAddress(
            @RequestParam(value = "keyword") String keyword) {
        List<ChargerSearchDTO> chargers = chargerService.searchChargersDTOByNameOrAddress(keyword);
        return createResponse(chargers);
    }

    @CrossOrigin("*")
    @GetMapping("/search/marker")
    public ResponseEntity<Response<List<ChargerMarkerDTO>>> searchChargersNearby(
            @RequestParam float swLat, @RequestParam float swLng,
            @RequestParam float neLat, @RequestParam float neLng) {
        List<ChargerMarkerDTO> charger = chargerService.searchChargersInArea(swLat, swLng, neLat, neLng);
        return createResponse(charger);
    }

    @CrossOrigin("*")
    @GetMapping("/{chargerId}/detail")
    public ResponseEntity<Response<ChargerDetailDTO>> getChargerDetail(@PathVariable String chargerId) {
        ChargerDetailDTO detail = chargerService.getChargerDetailById(chargerId);
        return createResponse(detail);
    }

    @CrossOrigin("*")
    @GetMapping("/all")
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

