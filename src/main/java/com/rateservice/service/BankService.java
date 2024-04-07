package com.rateservice.service;

import com.rateservice.dao.Bank;
import com.rateservice.dao.User;
import java.util.List;
import java.util.Set;

/** JavaDoc COMMENT. */
public interface BankService {
  List<Bank> getAllBanks();

  Bank saveBank(Bank bank);

  Bank updateBank(Long id, Bank newBank);

  void deleteBank(Long id);

  Bank findBankById(Long id);

  Bank addUser(User user, Long bkId);

  Bank addUserById(Long usId, Long bkId);

  Set<User> getAllBankUsers(Long bkId);
}
