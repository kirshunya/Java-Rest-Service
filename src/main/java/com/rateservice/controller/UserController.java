package com.rateservice.controller;

import com.rateservice.dao.Bank;
import com.rateservice.dao.PayCard;
import com.rateservice.dao.User;
import com.rateservice.service.UserService;
import io.micrometer.core.annotation.Timed;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/api/user")
public class UserController {
  private final UserService service;

  @GetMapping("/all")
  public List<User> getAllUsers() {
    return service.getAllUsers();
  }

  @PostMapping("/register")
  public User saveUser(@RequestBody User user) {
    return service.saveUser(user);
  }

  @PutMapping("/update_user/{id}")
  public User updateUser(@PathVariable Long id, @RequestBody User newUser) {
    return service.updateUser(id, newUser);
  }

  @DeleteMapping("/delete_user/{id}")
  public void deleteUser(@PathVariable Long id) {
    service.deleteUser(id);
  }

  @PostMapping("/add_card/{id}")
  public String addCardToUser(@PathVariable Long id, @RequestBody PayCard paycard) {
    return service.addCard(id, paycard);
  }

  @PutMapping("/update_card/{id}")
  public PayCard updateCard(
      @PathVariable Long id, @RequestBody PayCard payCard, @RequestParam Long cardId) {
    return service.updateCard(id, payCard, cardId);
  }

  @DeleteMapping("/delete_card/{usId}")
  public String deleteCardFromUser(@PathVariable Long usId, @RequestParam Long cardId) {
    return service.deleteCard(usId, cardId);
  }

  @PostMapping("/add_bank/{id}")
  public boolean addBankToUser(@PathVariable Long id, @RequestBody Bank bank) {
    return service.addBank(id, bank);
  }

  @PostMapping("/add_bank_by_id/{usId}")
  public User addBankToUserById(@PathVariable Long usId, @RequestParam Long bkId) {
    return service.addBankById(usId, bkId);
  }

  @Timed(
      value = "findUsersWithCreditDateGreaterThan.time",
      description = "Time taken to find users with credit date greater than")
  @GetMapping("/credit_date_greater_than/")
  public Object getUsersWithCreditDateGreaterThan(
      @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return service.findUsersWithCreditDateGreaterThan(date);
  }

  /** Bulk operation*/
  @PostMapping("/bulk_save_users/")
  public Set<User> bulkCreateUsers(@RequestBody List<User> users) {
    return service.bulkCreateUsers(users);
  }

  @GetMapping("/{userId}")
  public User getUserById(@PathVariable Long userId) {
    return service.getUserById(userId);
  }
}
