package com.rateservice.controller;

import com.rateservice.dao.Bank;
import com.rateservice.dao.User;
import com.rateservice.service.BankService;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** JavaDoc COMMENT. */
@RestController
@AllArgsConstructor
@RequestMapping("/api/bank")
public class BankController {
  private final BankService service;

  @GetMapping("/all")
  public List<Bank> getAllBanks() {
    return service.getAllBanks();
  }

  @PostMapping("/save_bank")
  public Bank saveBank(@RequestBody Bank user) {
    return service.saveBank(user);
  }

  @PutMapping("/update_bank/{id}")
  public Bank updateBank(@PathVariable Long id, @RequestBody Bank newBank) {
    return service.updateBank(id, newBank);
  }

  @DeleteMapping("/delete_bank/{id}")
  public void deleteBank(@PathVariable Long id) {
    service.deleteBank(id);
  }

  @PostMapping("/add_user_to_bank/{bkId}")
  public Bank addUser(@RequestBody User user, @PathVariable Long bkId) {
    return service.addUser(user, bkId);
  }

  @PostMapping("/add_user_by_id/{bkId}")
  public Bank addUserById(@RequestParam Long usId, @PathVariable Long bkId) {
    return service.addUserById(usId, bkId);
  }

  @GetMapping("/all_bank_users/{bkId}")
  public Set<User> getAllBankUsers(@PathVariable Long bkId) {
    return service.getAllBankUsers(bkId);
  }

  @GetMapping("/bank_by_id/{bkId}")
  public Bank getBankById(@PathVariable Long bkId) {
    return service.findBankById(bkId);
  }
}
