package com.pet.controller;

import com.pet.pojo.Valute;
import com.pet.service.ValuteService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@RestController
@RequestMapping("valute")
@RequiredArgsConstructor
public class ValuteController {
    private final ValuteService valuteService;

    @GetMapping("{valute}")
    public Mono<String> valute(@PathVariable("valute") String valute,
                              @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam("date_req") LocalDate date) {
        return valuteService.getValute(valute, date);
    }
}
