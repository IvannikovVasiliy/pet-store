package com.pet.service;

import com.pet.dto.ThingDto;
import com.pet.entity.Thing;
import com.pet.mapper.ThingMapper;
import com.pet.repository.ThingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThingService {
    private final ThingRepository thingRepository;
    //?????????????????????? static
    private final ThingMapper thingMapper;

    public List<ThingDto> getThings() {
        return thingRepository.findAll()
                .stream()
                .map(thing -> ThingDto
                        .builder()
                        .name(thing.getName())
                        .nameStore(thing.getStore().getName())
                        .nameCategory(thing.getCategory().getName())
                        .build())
                .toList();
    }

    public ThingDto createThing(ThingDto thingDto) {
        Thing thing = new Thing();
        thing = thingMapper.toEntity(thingDto);
        thingRepository.save(thing);

        return ThingDto
                .builder()
                .name(thing.getName())
                .nameStore(thing.getStore().getName())
                .nameCategory(thing.getCategory().getName())
                .price(thing.getPrice())
                .build();
    }

    public ThingDto thingById(Long id) {
        return thingMapper.toDto(
                thingRepository.findById(id).get()
        );
    }
}
