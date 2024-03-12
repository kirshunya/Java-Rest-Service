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

    private final RateService rateService;


    @GetMapping
    public  Mono<List<Rate>> getRates() {
        return rateService.getAllCurrencies();
    }

    @GetMapping("/rates")
    public Rate getRatesForCurrency(@RequestParam String name) {
        return rateService.getRateForCurrency(name);
    }

}
