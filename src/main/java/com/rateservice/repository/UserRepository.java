package com.rateservice.repository;

import com.rateservice.dao.User;
import java.time.LocalDate;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** JavaDoc COMMENT. */
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("SELECT u FROM User u JOIN u.credit c WHERE c.endOfCredit = :inputDate")
  Set<User> findUsersWithCreditDateGreaterThan(@Param("inputDate") LocalDate inputDate);
}
