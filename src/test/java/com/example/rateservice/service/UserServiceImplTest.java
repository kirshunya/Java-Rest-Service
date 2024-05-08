package com.example.rateservice.service;

import com.rateservice.dao.Bank;
import com.rateservice.dao.PayCard;
import com.rateservice.dao.User;
import com.rateservice.repository.BankRepository;
import com.rateservice.repository.PayCardsRepository;
import com.rateservice.repository.UserRepository;
import com.rateservice.service.impl.UserServiceImpl;
import com.rateservice.utilities.Cache;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
  @Mock private Cache cache;

  @Mock private UserRepository userRepository;

  @Mock private PayCardsRepository payCardsRepository;

  @Mock private BankRepository bankRepository;

  @InjectMocks private UserServiceImpl userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAllUsers() {
    // Arrange
    List<User> users = new ArrayList<>();
    User user1 = new User();
    User user2 = new User();
    users.add(user1);
    users.add(user2);
    Sort sort = Sort.by(Sort.Direction.ASC, "id");
    when(userRepository.findAll(sort)).thenReturn(users);

    // Act
    List<User> result = userService.getAllUsers();

    // Assert
    assertEquals(2, result.size());
    assertEquals(user1, result.get(0));
    assertEquals(user2, result.get(1));
    verify(userRepository, times(1)).findAll(sort);
  }

  @Test
  void testSaveUser() {
    // Arrange
    User user = new User();
    LocalDate currentDate = LocalDate.now();
    user.setDateOfReg(currentDate);
    when(userRepository.save(user)).thenReturn(user);

    // Act
    User result = userService.saveUser(user);

    // Assert
    assertNotNull(result);
    assertEquals(currentDate, result.getDateOfReg());
    verify(userRepository, times(1)).save(user);
  }

  @Test
  void testUpdateUser_UserExists() {
    // Arrange
    Long id = 1L;
    User existingUser = new User();
    existingUser.setId(id);
    existingUser.setFirstName("John");
    existingUser.setLastName("Doe");
    existingUser.setEmail("john.doe@example.com");

    User newUser = new User();
    newUser.setFirstName("Jane");
    newUser.setLastName("Smith");
    newUser.setEmail("jane.smith@example.com");

    when(userRepository.findById(id)).thenReturn(java.util.Optional.of(existingUser));
    when(userRepository.save(existingUser)).thenReturn(existingUser);

    // Act
    User result = userService.updateUser(id, newUser);

    // Assert
    assertNotNull(result);
    assertEquals(id, result.getId());
    assertEquals(newUser.getFirstName(), result.getFirstName());
    assertEquals(newUser.getLastName(), result.getLastName());
    assertEquals(newUser.getEmail(), result.getEmail());
    verify(userRepository, times(1)).findById(id);
    verify(userRepository, times(1)).save(existingUser);
  }

  @Test
  void testUpdateUser_UserDoesNotExist() {
    // Arrange
    Long id = 1L;
    User newUser = new User();
    newUser.setFirstName("Jane");
    newUser.setLastName("Smith");
    newUser.setEmail("jane.smith@example.com");

    when(userRepository.findById(id)).thenReturn(java.util.Optional.empty());

    // Act
    User result = userService.updateUser(id, newUser);

    // Assert
    assertNull(result);
    verify(userRepository, times(1)).findById(id);
    verify(userRepository, times(0)).save(any(User.class));
    verify(cache, times(0)).containsKey(any());
    verify(cache, times(0)).removeFromCache(any());
    verify(cache, times(0)).cacheUsersByDate(any(), any());
  }

  @Test
  void testDeleteUser_UserExists() {
    // Arrange
    Long id = 1L;
    User user = new User();
    user.setId(id);

    Bank bank = new Bank();
    bank.setId(1L);
    user.getBanks().add(bank);

    when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));

    // Act
    userService.deleteUser(id);

    // Assert
    verify(userRepository, times(1)).findById(id);
    verify(userRepository, times(1)).delete(user);
    verify(bankRepository, times(1)).save(bank);
  }

  @Test
  void testDeleteUser_UserDoesNotExist() {
    // Arrange
    Long id = 1L;

    when(userRepository.findById(id)).thenReturn(java.util.Optional.empty());

    // Act & Assert
    assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(id));
    verify(userRepository, times(1)).findById(id);
    verify(userRepository, times(0)).delete(any(User.class));
    verify(cache, times(0)).containsKey(any());
    verify(cache, times(0)).removeFromCache(any());
    verify(bankRepository, times(0)).save(any(Bank.class));
  }

  @Test
  void testFindUserById_UserExists() {
    // Arrange
    Long id = 1L;
    User user = new User();
    user.setId(id);

    when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user));

    // Act
    User result = userService.findUserById(id);

    // Assert
    assertNotNull(result);
    assertEquals(id, result.getId());
    verify(userRepository, times(1)).findById(id);
  }

  @Test
  void testFindUserById_UserDoesNotExist() {
    // Arrange
    Long id = 1L;
    when(userRepository.findById(id)).thenReturn(java.util.Optional.empty());

    // Act & Assert
    assertThrows(RuntimeException.class, () -> userService.findUserById(id));
    verify(userRepository, times(1)).findById(id);
  }

  @Test
  void testAddCard() {
    Long userId = 1L;
    PayCard card = new PayCard();
    User user = new User();
    user.setId(userId);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(userRepository.save(any(User.class))).thenReturn(user);

    String result = userService.addCard(userId, card);

    assertEquals("Card has been added.", result);
    verify(userRepository).save(user);
  }

  @Test
  void testUpdateCard() {
    Long userId = 1L;
    Long cardId = 2L;
    PayCard card = new PayCard();
    card.setId(cardId);
    User user = new User();
    user.setId(userId);
    PayCard existingCard = new PayCard();
    existingCard.setId(cardId);
    user.getPayCards().add(existingCard);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(userRepository.save(any(User.class))).thenReturn(user);

    PayCard result = userService.updateCard(userId, card, cardId);

    assertNotNull(result);
    verify(userRepository).save(user);
  }

  @Test
  void testDeleteCard() {
    Long userId = 1L;
    Long cardId = 2L;
    User user = new User();
    user.setId(userId);
    PayCard payCard = new PayCard();
    payCard.setId(cardId);
    user.getPayCards().add(payCard);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    // Удаляем строку, которая пытается настроить поведение deleteById, так как это не возвращает значение
    when(userRepository.save(any(User.class))).thenReturn(user);

    String result = userService.deleteCard(userId, cardId);

    assertEquals("Card has been removed.", result);
    // Проверяем, что метод deleteById был вызван с ожидаемым аргументом
    verify(payCardsRepository).deleteById(cardId);
  }


  @Test
  void testAddBank() {
    Long userId = 1L;
    Bank bank = new Bank();
    User user = new User();
    user.setId(userId);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(userRepository.save(any(User.class))).thenReturn(user);

    boolean result = userService.addBank(userId, bank);

    assertTrue(result);
    verify(userRepository).save(user);
  }

  @Test
  void testAddBankById() {
    Long userId = 1L;
    Long bankId = 2L;
    User user = new User();
    user.setId(userId);
    Bank bank = new Bank();
    bank.setId(bankId);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(bankRepository.findById(bankId)).thenReturn(Optional.of(bank));
    when(userRepository.save(any(User.class))).thenReturn(user);
    when(bankRepository.save(any(Bank.class))).thenReturn(bank);

    User result = userService.addBankById(userId, bankId);

    assertNotNull(result);
    verify(bankRepository).save(bank);
  }

  @Test
  void testFindUsersWithCreditDateGreaterThan() {
    LocalDate inputDate = LocalDate.now();
    Set<User> users = new HashSet<>();
    users.add(new User());

    when(userRepository.findUsersWithCreditDateGreaterThan(inputDate)).thenReturn(users);

    Object result = userService.findUsersWithCreditDateGreaterThan(inputDate);

    assertEquals(users, result);
    verify(cache).cacheUsersByDate(inputDate, users);
  }

  @Test
  void testBulkCreateUsers() {
    List<User> users = new ArrayList<>();
    users.add(new User());
    users.add(new User());

    when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Set<User> result = userService.bulkCreateUsers(users);

    assertEquals(users.size(), result.size());
    verify(userRepository, times(users.size())).save(any(User.class));
  }

}
