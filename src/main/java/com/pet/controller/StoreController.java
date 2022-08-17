package com.pet.controller;

import com.pet.dto.PageDto;
import com.pet.dto.StoreDto;
import com.pet.entity.StoreEntity;
import com.pet.model.StoreSearchCriteria;
import com.pet.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.h2.mvstore.db.Store;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("store")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public List<StoreDto> stores(PageDto pageDto, StoreSearchCriteria criteria) {
        return storeService.stores(pageDto);
    }

    @PostMapping()
    public ResponseEntity<String> createStore(@Valid @RequestBody StoreDto storeDto) {
        return ResponseEntity.ok(
                String.format("created store {}", storeService.createStore(storeDto).toString())
        );
    }

    @PutMapping("{id}")
    public StoreDto putStore(@PathVariable UUID id, @Valid @RequestBody StoreDto storeDto) {
        return storeService.putStore(id, storeDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable UUID id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok("deleted store");
    }

}
