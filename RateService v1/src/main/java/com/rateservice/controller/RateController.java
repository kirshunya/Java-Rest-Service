package com.rateservice.controller;

import com.rateservice.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
@RequestMapping("exchange_rate")
public class RateController {

    private final RateService service;

    @GetMapping
    public Flux<String> getRates() {
        return service.getAllCurrencies();
    }

    @GetMapping("/rates")
    public String getRatesForCurrency(@RequestParam String name) {
        return service.getRateForCurrency(name);
    }



}
