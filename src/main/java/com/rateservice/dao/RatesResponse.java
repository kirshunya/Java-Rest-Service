package com.rateservice.dao;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

/** JavaDoc COMMENT. */
@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class RatesResponse {
  private List<Rate> rates;
}
