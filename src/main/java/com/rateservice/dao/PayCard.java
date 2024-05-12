package com.rateservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/** JavaDoc COMMENT. */
@Entity
@Table(name = "pay_cards")
@Getter
@Setter
public class PayCard {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private double value;
  private short firstDigits;
  private short secondDigits;
  private short thirdDigits;
  private short fourthDigits;
  private LocalDate date;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
