package com.pet.service;

import com.pet.dto.StoreDto;
import com.pet.entity.City;
import com.pet.entity.StoreEntity;
import com.pet.repository.CityRepository;
import com.pet.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@WebMvcTest(StoreService.class)
@SpringBootTest
class StoreServiceTest {
    @MockBean
    private StoreRepository storeRepository;

    @MockBean
    private CityRepository cityRepository;

    @Autowired
    private StoreService storeService;

    @Test
    void findStoreById() {
        when(storeRepository.findById(any()))
                .thenReturn(Optional.of(
                        new StoreEntity(any(), "Apple", new City())
                ));

        StoreDto storeDto = storeService.findStoreById(UUID.randomUUID());
        assertEquals(storeDto.name, "Apple");
    }
}