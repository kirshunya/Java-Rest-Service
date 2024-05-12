package com.rateservice.dao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

/** JavaDoc COMMENT. */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;

  @Column(unique = true)
  private String email = "";

  private int sucCredits;
  private int failCredits;
  private LocalDate dateOfReg;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<PayCard> payCards = new HashSet<>();

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Bank> banks = new HashSet<>();

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Credit credit;
}
