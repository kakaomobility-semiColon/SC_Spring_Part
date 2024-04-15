package com.semicolon.springpart.Controller;

import org.springframework.ui.Model;
import com.semicolon.springpart.Service.ChargerService;
import com.semicolon.springpart.entity.ChargerApiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChargerController {

    private final ChargerService chargerService;

    @Autowired
    public ChargerController(ChargerService chargerService) {
        this.chargerService = chargerService;
    }

    @GetMapping("/chargers")
    public String getAllChargers(Model model) {
        List<ChargerApiEntity> chargers = chargerService.getAllChargers();
        model.addAttribute("chargers", chargers);
        return "chargers"; // 템플릿 파일의 이름
    }
}