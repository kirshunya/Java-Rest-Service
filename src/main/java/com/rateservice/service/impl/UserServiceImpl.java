package com.rateservice.service.impl;

import com.rateservice.dao.PayCard;
import com.rateservice.dao.User;
import com.rateservice.repository.PayCardsRepository;
import com.rateservice.repository.UserRepository;
import com.rateservice.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private PayCardsRepository cardsRepository;
    @Override
    public List<User> getAllUsers() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return repository.findAll(sort);
    }

    @Override
    public User saveUser(User user) {
        LocalDate currentDate = LocalDate.now();
        user.setDateOfReg(currentDate);
        return repository.save(user);
    }

    @Override
    public User updateUser(Long id, User newUser) {
        return repository.findById(id).map(u -> {
                    u.setFirstName(newUser.getFirstName());
                    u.setLastName(newUser.getLastName());
                    u.setEmail(newUser.getEmail());
                    u.setPayCards(newUser.getPayCards());
                    u.setDateOfReg(newUser.getDateOfReg());
                    u.setFailCredits(newUser.getFailCredits());
                    u.setSucCredits(newUser.getSucCredits());
                    return repository.save(u);
                })
                .orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        User userExisting = findUserById(id);
        if (userExisting != null) {
            repository.deleteById(id);
        }
    }

    @Override
    public User findUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public boolean addCard(Long usId, PayCard card) {
        User user = findUserById(usId);

        if (user != null) {
             card.setUser(user);
            user.getPayCards().add(card);
            repository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public PayCard updateCard(Long usId, PayCard card, Long cardId) {
        User user = findUserById(usId);
        PayCard cardToUpdate = user.getPayCards().stream()
                .filter(userCard -> userCard.getId().equals(cardId))
                .findFirst()
                .orElse(null);
        if (cardToUpdate != null) {
            // Изменение информации о карте
            cardToUpdate.setFirstDigits(card.getFirstDigits());
            cardToUpdate.setSecondDigits(card.getSecondDigits());
            cardToUpdate.setThirdDigits(card.getThirdDigits());
            cardToUpdate.setFourthDigits(card.getFourthDigits());
            cardToUpdate.setDate(card.getDate());
            cardToUpdate.setValue(card.getValue());
            cardToUpdate.setId(cardId);
            repository.save(user);
            return card;
        }
        return null;
    }


    @Override
    public String deleteCard(Long usId, Long cardId) {
        User user = repository.findById(usId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        PayCard payCard = user.getPayCards().stream()
                .filter(card -> card.getId().equals(cardId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("PayCard not found"));
        user.getPayCards().remove(payCard);
        cardsRepository.deleteById(cardId);
        repository.save(user);
        return "Card has been removed.";
    }


}
