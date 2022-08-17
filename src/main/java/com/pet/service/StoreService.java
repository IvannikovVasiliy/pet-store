package com.pet.service;

import com.pet.dto.PageDto;
import com.pet.dto.StoreDto;
import com.pet.entity.City;
import com.pet.entity.StoreEntity;
import com.pet.error.ResourceAlreadyExistsException;
import com.pet.error.ResourceNotFoundException;
import com.pet.repository.CityRepository;
import com.pet.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class StoreService {
    private final StoreRepository storeRepository;
    private final CityRepository cityRepository;

    public StoreDto createStore(@Valid StoreDto storeDto) {
        if (storeRepository.existsByName(storeDto.name)) {
            throw new ResourceAlreadyExistsException("Store with this name already exists");
        }

        City city = cityRepository.findByName(storeDto.cityName);
        if (city == null) {
            throw new ResourceNotFoundException(String.format("City with name %s not found", storeDto.cityName));
        }

        StoreEntity store = new StoreEntity();
        store.setName(storeDto.name);
        store.setCity(city);
        storeRepository.save(store);

        return storeDto;
    }

    public List<StoreDto> stores(PageDto pageDto) {
        return storeRepository
                .findAll(pageDto.getPageable())
                .stream()
                .map(store -> StoreDto.builder().name(store.getName()).cityName(store.getCity().getName()).build())
                .toList();
    }

    public StoreDto putStore(UUID id, StoreDto storeDto) {
        StoreEntity store = storeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("store with this id not founded"));
        store.setName(storeDto.name);
        store.setCity(
                cityRepository.findByName(storeDto.cityName)
        );

        storeRepository.save(store);
        return storeDto;
    }

    public void deleteStore(UUID id) {
        try {
            StoreEntity store = storeRepository
                    .findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("The store with this id not found"));
            storeRepository.deleteById(UUID.fromString("71be7226-d323-4edb-b7ee-d0d824d35f36"));
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("The store with this id not found");
        }
    }
}
