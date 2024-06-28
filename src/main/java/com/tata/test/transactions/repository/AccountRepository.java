package com.tata.test.transactions.repository;

import com.tata.test.transactions.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Accounts, Long> {

    Optional<Accounts> findByAccountNumberAndStatusIsTrue(String accountNumber);

}
