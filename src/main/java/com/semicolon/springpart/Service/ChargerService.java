package com.semicolon.springpart.Service;

import com.semicolon.springpart.Repository.ChargerRepository;
import com.semicolon.springpart.entity.ChargerApiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargerService {

    private final ChargerRepository chargerRepository;

    @Autowired
    public ChargerService(ChargerRepository chargerRepository) {
        this.chargerRepository = chargerRepository;
    }

    public List<ChargerApiEntity> getAllChargers() {
        return chargerRepository.findAll();
    }
}