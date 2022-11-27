package com.pet.service;

import com.pet.dto.ThingDto;
import com.pet.dto.ValuteDto;
import com.pet.entity.Thing;
import com.pet.mapper.ThingMapper;
import com.pet.pojo.Valute;
import com.pet.repository.ThingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThingService {
    private final ThingRepository thingRepository;
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
                        .nameCountry(thing.getCountry().getName())
                        .price(thing.getPrice().doubleValue())
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
                .price(thing.getPrice().doubleValue())
                .build();
    }

    public ThingDto thingById(Long id, String valute) {
        Thing thing = thingRepository.findById(id).get();

        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String url = "http://localhost:8081/cb/" + valute + "?date=" + DATE_FORMATTER.format(LocalDate.now());

        RestTemplate restTemplate = new RestTemplate();
        ValuteDto valuteDto = restTemplate.getForObject(url, ValuteDto.class);
        System.out.println(valuteDto.value);

        valuteDto.value = valuteDto.value.replace(',', '.');
        System.out.println(valuteDto.value);

        ThingDto result = thingMapper.toDto(thing);
        Double price = Double.valueOf(valuteDto.value);
        result.price = thing.getPrice() / price;

        return result;
    }
}
