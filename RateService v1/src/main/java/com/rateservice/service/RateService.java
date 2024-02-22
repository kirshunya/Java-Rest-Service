package com.rateservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rateservice.dao.Rate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class RateService {
    private static final String API_URL = "https://developerhub.alfabank.by:8273/partner/1.0.1/public/rates";
    public Flux<String> getAllCurrencies() {
        WebClient webClient = WebClient.create();
        return webClient.get()
                .uri(API_URL)
                .retrieve()
                .bodyToFlux(String.class);
    }


    public String getRateForCurrency(String name){
        return "Result " + name;
    }
}
