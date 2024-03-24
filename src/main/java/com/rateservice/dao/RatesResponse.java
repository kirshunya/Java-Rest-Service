package com.rateservice.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class RatesResponse {
    private List<Rate> rates;
}
