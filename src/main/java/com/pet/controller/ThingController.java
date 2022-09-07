package com.pet.controller;

import com.pet.dto.ThingDto;
import com.pet.entity.Thing;
import com.pet.service.ThingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thing")
@RequiredArgsConstructor
public class ThingController {
    private final ThingService thingService;

    @GetMapping
    public List<ThingDto> things() {
        return thingService.getThings();
    }

    @GetMapping("{id}")
    public ThingDto thingById(@PathVariable("id") Long id) {
        return thingService.thingById(id);
    }

    @PostMapping
    public ThingDto createThing(@RequestBody ThingDto thingDto) {
        return thingService.createThing(thingDto);
    }
}
