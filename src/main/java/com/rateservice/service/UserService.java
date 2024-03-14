package com.rateservice.service;

import com.rateservice.dao.PayCard;
import com.rateservice.dao.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);
    User updateUser(Long id, User newUser);
    void deleteUser(Long id);
    User findUserById(Long id);
    boolean addCard(Long usId, PayCard card);
    PayCard updateCard(Long usId, PayCard card, Long cardId);
    String deleteCard(Long usId, Long cardId);
}
