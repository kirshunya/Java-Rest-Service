package com.rateservice.controller;

import com.rateservice.dao.Rate;
import com.rateservice.service.RateService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/** JavaDoc COMMENT. */
@RestController
@AllArgsConstructor
@RequestMapping("exchange_rate")
public class RateController {

  private final RateService rateService;

  @GetMapping
  public Mono<List<Rate>> getRates() {
    return rateService.getAllCurrencies();
  }

  @GetMapping("/rates")
  public Rate getRatesForCurrency(@RequestParam String name) {
    return rateService.getRateForCurrency(name);
  }
}
