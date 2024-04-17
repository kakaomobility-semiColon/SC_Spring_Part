package com.semicolon.springpart.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public String getChargersPage(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  Model model) {
        // PageRequest.of(page, size)를 사용하여 페이지 번호와 크기를 설정
        Page<ChargerApiEntity> chargersPage = chargerService.getAllChargersPageable(PageRequest.of(page, size));

        // 현재 페이지의 ChargerApiEntity 목록을 모델에 추가
        model.addAttribute("chargers", chargersPage.getContent());

        // 페이징 관련 정보를 모델에 추가
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", chargersPage.getTotalPages());
        model.addAttribute("totalItems", chargersPage.getTotalElements());

        return "chargers";
    }
}
