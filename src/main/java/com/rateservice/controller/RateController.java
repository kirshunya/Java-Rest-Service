package com.rateservice.controller;

import com.rateservice.dao.Rate;
import com.rateservice.service.RateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("exchange_rate")
public class RateController {

    private final RateService service;

    @GetMapping
    public  Mono<List<Rate>> getRates() {
        return service.getAllCurrencies();
    }

    @GetMapping("/rates")
    public Rate getRatesForCurrency(@RequestParam String name) {
        return service.getRateForCurrency(name);
    }



}
