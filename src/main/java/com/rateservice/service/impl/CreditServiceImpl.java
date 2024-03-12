package com.rateservice.service.impl;

import com.rateservice.dao.Credit;
import com.rateservice.repository.CreditRepository;
import com.rateservice.service.CreditService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class CreditServiceImpl implements CreditService {
    private final CreditRepository repository;
    @Override
    public List<Credit> getAllCredits() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return repository.findAll(sort);
    }

    @Override
    public Credit saveCredit(Credit credit) {
        return repository.save(credit);
    }

    @Override
    public Credit updateCredit(Long id, Credit newCredit) {
        Credit credit = repository.findById(id).orElseThrow(() -> new RuntimeException("Credit not found"));

        credit.setValue(newCredit.getValue());
        return repository.save(credit);
    }

    @Override
    public void deleteCredit(Long id) {
        Credit creditExisting = findCreditById(id);
        if (creditExisting != null) {
            repository.deleteById(id);
        }
    }

    @Override
    public Credit findCreditById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Credit not found with id: " + id));
    }
}