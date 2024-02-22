package com.rateservice.dao;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class Rate {

     private float sellRate;
     private String sellIso;
     private int sellCode;
     private float buyRate;
     private String buyIso;
     private int buyCode;
     private int quantity;
     private String name;
     private String date;

}


