package com.semicolon.springpart.Controller;

import com.semicolon.springpart.Service.ChargerService;
import com.semicolon.springpart.entity.ChargerApiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/charger")
public class ChargerController {

    private final ChargerService chargerService;

    @Autowired
    public ChargerController(ChargerService chargerService) {
        this.chargerService = chargerService;
    }

    @GetMapping("/search")
    public List<ChargerApiEntity> searchChargersNearby(@RequestParam float swLat,
                                                       @RequestParam float swLng,
                                                       @RequestParam float neLat,
                                                       @RequestParam float neLng) {
        return chargerService.searchChargersInArea(swLat, swLng, neLat, neLng);
    }

    @GetMapping("/{chargerId}/detail")
    public ChargerApiEntity getChargerDetail(@PathVariable String chargerId) {
        return chargerService.getChargerDetailById(chargerId);
    }
}