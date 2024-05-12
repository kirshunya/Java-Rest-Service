package com.example.rateservice.service;

import com.rateservice.dao.Credit;
import com.rateservice.dao.PayCard;
import com.rateservice.dao.User;
import com.rateservice.repository.CreditRepository;
import com.rateservice.repository.PayCardsRepository;
import com.rateservice.repository.UserRepository;
import com.rateservice.service.impl.CreditServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreditServiceImplTest {

  @Mock
  private CreditRepository repository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private PayCardsRepository payCardsRepository;

  @InjectMocks
  private CreditServiceImpl creditService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAllCredits() {
    List<Credit> credits = new ArrayList<>();
    credits.add(new Credit());

    when(repository.findAll(any(Sort.class))).thenReturn(credits);

    List<Credit> result = creditService.getAllCredits();

    assertEquals(credits, result);
  }


  @Test
  void testSaveCredit() {
    Credit credit = new Credit();

    when(repository.save(any(Credit.class))).thenReturn(credit);

    Credit result = creditService.saveCredit(credit);

    assertEquals(credit, result);
  }

  @Test
  void testUpdateCredit() {
    Long id = 1L;
    Credit newCredit = new Credit();
    Credit credit = new Credit();

    when(repository.findById(id)).thenReturn(Optional.of(credit));
    when(repository.save(any(Credit.class))).thenReturn(credit);

    Credit result = creditService.updateCredit(id, newCredit);

    assertEquals(credit, result);
  }

  @Test
  void testDeleteCredit() {
    Long id = 1L;
    User user = new User();
    Credit credit = new Credit();
    user.setCredit(credit);

    when(userRepository.findById(id)).thenReturn(Optional.of(user));
    when(userRepository.save(any(User.class))).thenReturn(user);
    when(repository.save(any(Credit.class))).thenReturn(credit);

    creditService.deleteCredit(id);

    verify(repository).delete(credit);
  }

  @Test
  void testFindCreditById() {
    Long id = 1L;
    Credit credit = new Credit();

    when(repository.findById(id)).thenReturn(Optional.of(credit));

    Credit result = creditService.findCreditById(id);

    assertEquals(credit, result);
  }

  @Test
  void testAddCreditToUserById() {
    Long crId = 1L;
    Long usId = 2L;
    User user = new User();
    Credit credit = new Credit();

    when(userRepository.findById(usId)).thenReturn(Optional.of(user));
    when(repository.findById(crId)).thenReturn(Optional.of(credit));
    when(repository.save(any(Credit.class))).thenReturn(credit);

    Credit result = creditService.addCreditToUserById(crId, usId);

    assertEquals(credit, result);
  }

  @Test
  void testAddCreditToUser() {
    Credit credit = new Credit();
    Long usId = 1L;
    User user = new User();

    when(userRepository.findById(usId)).thenReturn(Optional.of(user));
    when(userRepository.save(any(User.class))).thenReturn(user);

    Credit result = creditService.addCreditToUser(credit, usId);

    assertEquals(credit, result);
  }

  @Test
  void testCreditPay() {
    Long usId = 1L;
    Long cardId = 2L;
    User user = new User();
    Credit credit = new Credit();
    PayCard card = new PayCard();
    user.setCredit(credit);
    card.setValue(1000);
    credit.setValue(500);

    when(userRepository.findById(usId)).thenReturn(Optional.of(user));
    when(payCardsRepository.findById(cardId)).thenReturn(Optional.of(card));
    when(repository.save(any(Credit.class))).thenReturn(credit);

    String result = creditService.creditPay(usId, cardId);

    assertEquals("Credit successful paid.", result);
  }


}
