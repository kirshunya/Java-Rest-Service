package com.rateservice.service;

import com.rateservice.dao.Rate;
import com.rateservice.dao.RatesResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/** JavaDoc COMMENT. */
@Service
@AllArgsConstructor
public class RateService {
  private static final String API_URL =
      "https://developerhub.alfabank.by:8273/partner/1.0.1/public/rates";

  /** JavaDoc COMMENT. */
  public Mono<List<Rate>> getAllCurrencies() {
    WebClient webClient = WebClient.create();
    return webClient
        .get()
        .uri(API_URL)
        .retrieve()
        .bodyToMono(RatesResponse.class)
        .map(RatesResponse::getRates);
  }

  public Rate getRateForCurrency(String name) {
    return new Rate(96, "EUR", 978, 106, "RUB", 643, 1, name, "23.02.2024");
  }
}
