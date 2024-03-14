package com.rateservice.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
    private String email;
    private byte sucCredits;
    private byte failCredits;
    private LocalDate dateOfReg;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id")
    //@Column(unique = true)
    private Set<PayCard> payCards;




}
