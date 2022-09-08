package com.cb.controller;

import com.cb.model.Valute;
import com.cb.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cb")
public class HomeController {
    private final Valute valute;
    private final CurrencyRateService currencyRateService;

    @GetMapping("/{valute}")
    public Valute getValute(@PathVariable("valute") String valute,
                            @DateTimeFormat(pattern = "dd-MM-yyyy") @RequestParam("date") LocalDate date) {
        return currencyRateService.getValute(valute, date);
    }
}
