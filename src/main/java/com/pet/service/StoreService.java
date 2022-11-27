package com.pet.service;

import com.pet.dto.CityDto;
import com.pet.dto.CountryDto;
import com.pet.dto.StoreDto;
import com.pet.pojo.StoreModel;
import com.pet.entity.City;
import com.pet.entity.StoreEntity;
import com.pet.error.ResourceAlreadyExistsException;
import com.pet.error.ResourceNotFoundException;
import com.pet.repository.CityRepository;
import com.pet.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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

    public StoreDto findStoreById(Long id) {
        return storeRepository.findById(id)
                .map(store -> StoreDto
                        .builder()
                        .name(store.getName())
                        .build())
                .get();
    }

    public List<StoreModel> stores(/*PageDto pageDto, StoreSearchCriteria criteria*/) {
        return storeRepository
                .findAll(/*new StoreSpecification(criteria), pageDto.getPageable()*/)
                .stream()
                .map(store -> {
                    City city = store.getCity();

                    CountryDto countryDto = CountryDto
                            .builder()
                            .name(city.getCountry().getName())
                            .build();

                    CityDto cityDto = CityDto
                            .builder()
                            .name(city.getName())
                            .country(countryDto)
                            .build();

                    return StoreModel
                            .builder()
                            .name(store.getName())
                            .city(cityDto)
                            .build();
                        }
                )
                .toList();
    }

    public StoreModel putStore(Long id, StoreModel storeModel) {
        StoreEntity store = storeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("store with this id not founded"));
        store.setName(storeModel.name);
        store.setCity(
                cityRepository.findByName(storeModel.cityName)
        );

        storeRepository.save(store);
        return storeModel;
    }

    public void deleteStore(Long id) {
        try {
            StoreEntity store = storeRepository
                    .findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("The store with this id not found"));
            storeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("The store with this id not found");
        }
    }
}
