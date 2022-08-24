package com.pet.service;

import com.pet.entity.City;
import com.pet.entity.Country;
import com.pet.repository.CityRepository;
import com.pet.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityServiceTest {

    @Autowired
    private CityService cityService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    private City city;

    @AfterEach
    void deleteEntity() {
        cityRepository.delete(city);
    }

    @Test
    void cities() {
        System.out.println(cityService.cities().size());
    }

    @Test
    void createCity() {
        city = new City();
        city.setName("Saint-Petersburg");
        city.setCountry(countryRepository.findById(2L).get());
        cityRepository.save(city);
    }
}