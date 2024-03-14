package com.rateservice.repository;

import com.rateservice.dao.PayCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayCardsRepository extends JpaRepository<PayCard, Long> {

}
