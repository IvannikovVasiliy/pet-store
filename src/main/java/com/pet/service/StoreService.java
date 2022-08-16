package com.pet.service;

import com.pet.entity.Store;
import com.pet.repository.StoreRepository;
import com.pet.dto.StoreDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Validated
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreDto createStore(@Valid StoreDto storeDto) {

        return storeDto;
    }
}
