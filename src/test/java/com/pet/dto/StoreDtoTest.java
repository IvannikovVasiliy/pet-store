package com.pet.dto;

import com.pet.entity.City;
import com.pet.entity.Country;
import com.pet.entity.StoreEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class StoreDtoTest {

    @Autowired
    private JacksonTester<StoreDto> json;

    @Test
    void toDtoTest() {
        StoreDto storeDto = StoreDto.toDto(new StoreEntity(
                UUID.randomUUID(),
                "Apple",
                new City(1L, "Moscow", new Country(1L, "Russia"))
                )
        );
        assertEquals("Apple", storeDto.name);
        assertEquals("Moscow", storeDto.cityName);
    }

    @Test
    void toEntityTest() {
        StoreEntity store = StoreDto.builder()
                .name("Apple")
                .cityName("Moscow")
                .build()
                .toEntity();

        assertEquals("Apple", store.getName());
    }

//    @Test
//    void testSerializePerson() throws Exception {
//        StoreDto storeDto = StoreDto.builder()
//                .name("Apple")
//                .cityName("Moscow")
//                .build();
//
//        StoreEntity domain = storeDto.toEntity();
//
//        assertThat(this.json.write(storeDto))
//                .isStrictlyEqualToJson("simple-person.json");
//    }
}