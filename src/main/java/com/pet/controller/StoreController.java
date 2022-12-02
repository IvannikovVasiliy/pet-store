package com.pet.controller;

import com.pet.dto.PageDto;
import com.pet.dto.StoreDto;
import com.pet.pojo.StoreModel;
import com.pet.repository.StoreRepository;
import com.pet.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final StoreRepository storeRepository;

    @GetMapping
    public List<StoreModel> stores(PageDto pageDto/*, StoreSearchCriteria criteria*/) {
        return storeService.stores(/*pageDto/*, criteria*/);
    }

    @GetMapping("{id}")
    public StoreDto storeById(@PathVariable UUID id) {
        return storeService.findStoreById(id);
    }

    @PostMapping()
    public ResponseEntity<String> createStore(@Valid @RequestBody StoreDto storeDto) {
        return ResponseEntity.ok(
                String.format("The store {} is CREATED", storeService.createStore(storeDto))
        );
    }

    @PutMapping("{id}")
    public StoreModel putStore(@PathVariable UUID id, @Valid @RequestBody StoreModel storeModel) {
        return storeService.putStore(id, storeModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable UUID id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok("deleted store");
    }

}
