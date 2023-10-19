package com.ekwateur.testTechnique.dao.repository;

import com.ekwateur.testTechnique.dao.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByClientReference(String clientReference);
}
