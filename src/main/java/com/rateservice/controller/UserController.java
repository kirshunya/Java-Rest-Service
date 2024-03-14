package com.rateservice.controller;

import com.rateservice.dao.PayCard;
import com.rateservice.dao.User;
import com.rateservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")

public class UserController {
    private final UserService service;

    @GetMapping("/all")
    public List<User> getAllUsers() { return service.getAllUsers(); }

    @PostMapping("/register")
    public User saveUser(@RequestBody User user) { return service.saveUser(user); }

    @PutMapping("/update_user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User newUser) { return service.updateUser(id, newUser);}

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) { service.deleteUser(id);}

    @PostMapping("/add_card/{id}")
    public boolean addCardToUser(@PathVariable Long id, @RequestBody PayCard paycard) { return service.addCard(id, paycard); }

    @PutMapping("/update_card/{id}")
    public PayCard updateCard(@PathVariable Long id, @RequestBody PayCard payCard, @RequestParam Long cardId) { return service.updateCard(id, payCard, cardId); }

    @DeleteMapping("/delete_card/{usId}")
    public String deleteCardFromUser(@PathVariable Long usId, @RequestParam Long cardId) { return service.deleteCard(usId, cardId); }

}