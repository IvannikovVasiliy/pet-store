package com.pet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.dto.CityDto;
import com.pet.dto.PageDto;
import com.pet.dto.StoreDto;
import com.pet.entity.City;
import com.pet.entity.StoreEntity;
import com.pet.pojo.StoreModel;
import com.pet.repository.CityRepository;
import com.pet.repository.StoreRepository;
import com.pet.service.StoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(StoreController.class)  - работает без авторизации
@SpringBootTest
@AutoConfigureMockMvc
public class StoreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private StoreController storeController;

    @MockBean
    private StoreService storeService;

    @MockBean // заглушка
    //@Autowired// - реальный бин
    // Отличия - реально в базу не лезем
    private StoreRepository storeRepository;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testNullableController() {
        Assertions.assertNotNull(storeController);
    }

    @Test
    public void stores() throws Exception {
        PageDto pageDto = new PageDto();
        pageDto.setPageNumber(0);
        pageDto.setPageSize(2);

        when(storeService.stores())
                .thenReturn(List.of(
                        StoreModel.builder().name("Apple").build()
                        ));

        mockMvc.perform(get("/store"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("Apple")))
                .andDo(print());
    }

    @Test
    void storeById() throws Exception {
        CityDto cityDto = CityDto.builder().name("Москва").build();

        when(storeService.findStoreById(
                ArgumentMatchers.any())
        )
                .thenReturn(StoreDto
                        .builder()
                        .name("Apple")
                        .cityName("Москва")
                        .build());

        mockMvc.perform(get("/store/4ecab507-5663-4ac5-9a7a-5449e1f0fddc\t"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", equalTo("Apple")));
    }

    @Test
    void createStore() throws Exception {
        StoreDto storeDto = StoreDto
                .builder()
                .name("Apple")
                .cityName("Москва")
                .build();

        Mockito.when(storeService.createStore(storeDto)).thenReturn(storeDto);

        mockMvc.perform(post("/store")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(storeDto)))
                .andExpect(status().isOk());
    }
}