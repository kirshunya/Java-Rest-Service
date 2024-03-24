package com.rateservice.service.impl;

import com.rateservice.dao.Bank;
import com.rateservice.dao.User;
import com.rateservice.repository.BankRepository;
import com.rateservice.repository.UserRepository;
import com.rateservice.service.BankService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Primary
public class BankServiceImpl implements BankService {
    private static final String BANK_NOT_FOUND = "Bank not found";

    private final BankRepository repository;
    private final UserRepository userRepository;
    @Override
    public List<Bank> getAllBanks() {
        return repository.findAll();
    }

    @Override
    public Bank saveBank(Bank bank) {
        return repository.save(bank);
    }

    @Override
    public Bank updateBank(Long id, Bank newBank) {
        Bank bank = repository.findById(id).orElseThrow(() -> new RuntimeException(BANK_NOT_FOUND));
            bank.setName(newBank.getName());
            bank.setUsers(newBank.getUsers());
            bank.setDateOfFoundation(newBank.getDateOfFoundation());
            bank.setPercentPerYear(newBank.getPercentPerYear());
        return repository.save(bank);
    }

    @Override
    public void deleteBank(Long id) {
        Bank bank = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(BANK_NOT_FOUND + id));
        for (User user : bank.getUsers()) {
            user.getBanks().remove(bank);
            userRepository.save(user);
        }
        repository.delete(bank);
    }

    @Override
    public Bank findBankById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Bank not found with id: " + id));
    }

    @Override
    public Bank addUserById(Long usId, Long bkId) {
        User user = userRepository.findById(usId).orElseThrow(() -> new RuntimeException("User not found"));
        Bank bank = repository.findById(bkId).orElseThrow(() -> new RuntimeException(BANK_NOT_FOUND));
        user.getBanks().add(bank);
        bank.getUsers().add(user);
        bank.setNumberOfUsers(bank.getNumberOfUsers() + 1);
        userRepository.save(user);
        repository.save(bank);
        return bank;
    }

    @Override
    public Set<User> getAllBankUsers(Long bkId) {
        Bank bank = repository.findById(bkId).orElseThrow(() -> new RuntimeException(BANK_NOT_FOUND));
        if (bank != null) {
            return bank.getUsers();
        }
        return Collections.emptySet();
    }

    @Override
    public Bank addUser(User user, Long bkId) {
        Bank bank = repository.findById(bkId).orElseThrow(() -> new RuntimeException(BANK_NOT_FOUND + "with id: " + bkId));
        bank.getUsers().add(user);
        bank.setNumberOfUsers(+1);
        repository.save(bank);
        return bank;
    }


}
