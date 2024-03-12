package com.rateservice.repository;

import com.rateservice.dao.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {

}
