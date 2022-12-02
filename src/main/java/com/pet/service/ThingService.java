package com.pet.service;

import com.pet.dto.ThingDto;
import com.pet.entity.Thing;
import com.pet.mapper.ThingMapper;
import com.pet.repository.ThingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThingService {
    private final ThingRepository thingRepository;
    //?????????????????????? static
    private final ThingMapper thingMapper;
    private final ValuteService valuteService;

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

    public ThingDto thingById(Long id, String valute) {
        Thing thing = thingRepository.findById(id).get();

        valuteService.getValute(valute, LocalDate.now());


        return thingMapper.toDto(thing);
    }
}
