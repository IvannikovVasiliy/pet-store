package com.pet.service;

import com.pet.dto.CityDto;
import com.pet.dto.CountryDto;
import com.pet.repository.CityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    //@Autowired
    private final CityRepository cityRepository;

    public List<CityDto> cities() {
        return cityRepository
                .findAll()
                .stream()
                .map(city -> CityDto.builder()
                        .name(city.getName())
                        .country(CountryDto.builder()
                                .name(city.getCountry().getName())
                                .build())
                        .build())
                .toList();
    }
}
