package com.rateservice.service;

import com.rateservice.dao.Credit;


import java.util.List;


public interface CreditService {
    List<Credit> getAllCredits();
    Credit saveCredit(Credit credit);
    Credit updateCredit(Long id, Credit newCredit);
    void deleteCredit(Long id);
    Credit findCreditById(Long id);
}
