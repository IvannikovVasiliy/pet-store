package com.pet.controller;

import com.pet.dto.StoreDto;
import com.pet.entity.Store;
import com.pet.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping()
    public ResponseEntity<String> createStore(@RequestBody @Valid StoreDto storeDto) {
        return ResponseEntity.ok("valid");
    }

}
