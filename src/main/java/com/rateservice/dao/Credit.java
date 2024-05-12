package com.rateservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

/** JavaDoc COMMENT. */
@Entity
@Table(name = "bank_credits")
@Getter
@Setter
public class Credit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private float value;
  private LocalDate endOfCredit;

  @JsonIgnore
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
}
