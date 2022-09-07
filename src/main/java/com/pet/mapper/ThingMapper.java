package com.pet.mapper;

import com.pet.dto.ThingDto;
import com.pet.entity.Category;
import com.pet.entity.Thing;
import com.pet.repository.CategoryRepository;
import com.pet.repository.CountryRepository;
import com.pet.repository.StoreRepository;
import com.pet.repository.ThingRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ThingMapper {
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;
    private final CountryRepository countryRepository;

    public Thing toEntity(ThingDto thingDto) {
        Thing thing = new Thing();
        thing.setName(thingDto.name);
        thing.setStore(storeRepository.findByName(thingDto.nameStore));
        thing.setCategory(categoryRepository.findByName(thingDto.nameCategory));
        thing.setCountry(countryRepository.findByName(thingDto.nameCountry));
        thing.setPrice(thingDto.price);

        return thing;
    }

    public ThingDto toDto(Thing thing) {
        return ThingDto
                .builder()
                .name(thing.getName())
                .nameCategory(thing.getCategory().getName())
                .nameStore(thing.getStore().getName())
                .nameCountry(thing.getCountry().getName())
                .price(thing.getPrice())
                .build();
    }
}
