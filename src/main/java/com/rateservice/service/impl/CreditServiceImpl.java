package com.rateservice.service.impl;


import com.rateservice.dao.Credit;
import com.rateservice.dao.PayCard;
import com.rateservice.dao.User;
import com.rateservice.repository.CreditRepository;
import com.rateservice.repository.PayCardsRepository;
import com.rateservice.repository.UserRepository;
import com.rateservice.service.CreditService;
import jakarta.persistence.Cacheable;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



import java.util.List;


@Service
@AllArgsConstructor
@Primary
@Cacheable

public class CreditServiceImpl implements CreditService {
    private final CreditRepository repository;
    private final UserRepository userRepository;
    private final PayCardsRepository payCardsRepository;

    @Override

    public List<Credit> getAllCredits() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return repository.findAll(sort);
    }

    @Override
    //@CachePut(value = "credits", key = "#credit.id")
    public Credit saveCredit(Credit credit) {
        return repository.save(credit);
    }

    @Override
    //@CachePut(value = "credits", key = "#credit.id")
    public Credit updateCredit(Long id, Credit newCredit) {
        Credit credit = repository.findById(id).orElseThrow(() -> new RuntimeException("Credit not found"));
        credit.setValue(newCredit.getValue());
        credit.setEndOfCredit(newCredit.getEndOfCredit());
        return repository.save(credit);
    }

    @Override
    //@CacheEvict(value = "credits", key = "#id")
    public void deleteCredit(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));
        Credit credit = user.getCredit();
        if (credit != null) {
            user.setCredit(null);
            credit.setUser(null);
            userRepository.save(user);
            repository.save(credit);
            repository.delete(credit);
        }
    }

    @Override
    public Credit findCreditById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Credit not found with id: " + id));
    }

    @Override
    public Credit addCreditToUserById(Long crId, Long usId) {
        User user = userRepository.findById(usId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + usId));
        Credit credit = repository.findById(crId)
                .orElseThrow(() -> new RuntimeException("Credit not found with id " + crId));
        credit.setUser(user);
        user.setCredit(credit);
        return repository.save(credit);
    }

    @Override
    public Credit addCreditToUser(Credit credit, Long usId) {
        User user = userRepository.findById(usId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setCredit(credit);
        userRepository.save(user);
        return credit;
    }

    @Override
    public String creditPay(Long usId, Long cardId) {
        User user = userRepository.findById(usId).orElseThrow(() -> new RuntimeException("User not found"));
        PayCard card = payCardsRepository.findById(cardId).orElseThrow(() -> new RuntimeException("PayCard not found with id: " + cardId));
        if (card.getValue() >= user.getCredit().getValue()) {
            card.setValue(card.getValue() - user.getCredit().getValue());
            deleteCredit(usId);
            user.setSucCredits(user.getSucCredits() + 1);
            return "Credit successful paid.";
        }
        return "Credit not paid.";
    }

}