package com.rateservice.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    private byte sucCredits;
    private byte failCredits;
    private LocalDate dateOfReg;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PayCard> payCards = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Bank> banks = new HashSet<>();

}
