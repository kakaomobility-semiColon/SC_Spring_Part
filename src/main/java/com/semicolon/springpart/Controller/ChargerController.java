package com.semicolon.springpart.Controller;

import com.semicolon.springpart.Service.BookmarkService;
import com.semicolon.springpart.Service.ChargerService;
import com.semicolon.springpart.dto.ChargerDetailDTO;
import com.semicolon.springpart.dto.ChargerMarkerDTO;
import com.semicolon.springpart.dto.ChargerSearchDTO;
import com.semicolon.springpart.dto.Response;
import com.semicolon.springpart.entity.BookmarkEntity;
import com.semicolon.springpart.entity.ChargerApiEntity;
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

    @GetMapping("/search")
    public ResponseEntity<Response<List<ChargerSearchDTO>>> searchChargersByNameOrAddress(
            @RequestParam(value = "keyword") String keyword) {
        List<ChargerSearchDTO> chargers = chargerService.searchChargersDTOByNameOrAddress(keyword);
        return createResponse(chargers);
    }

    @GetMapping("/search/marker")
    public ResponseEntity<Response<List<ChargerMarkerDTO>>> searchChargersNearby(
            @RequestParam float swLat, @RequestParam float swLng,
            @RequestParam float neLat, @RequestParam float neLng) {
        List<ChargerMarkerDTO> charger = chargerService.searchChargersInArea(swLat, swLng, neLat, neLng);
        return createResponse(charger);
    }

    @GetMapping("/{chargerId}/detail")
    public ResponseEntity<Response<ChargerDetailDTO>> getChargerDetail(@PathVariable String chargerId) {
        ChargerDetailDTO detail = chargerService.getChargerDetailById(chargerId);
        return createResponse(detail);
    }

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
