package com.rateservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "bank")
@Getter
@Setter

public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int numberOfUsers;
    private int percentPerYear;
    private LocalDate dateOfFoundation;
    @JsonIgnore
    @ManyToMany(mappedBy = "banks", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<User> users = new HashSet<>();


}
