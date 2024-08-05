package com.transaction.dev.clients.repository;

import com.transaction.dev.clients.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Clients, Long> {

    Optional<Clients> findByIdAndStatusIsTrue(Long id);



}
