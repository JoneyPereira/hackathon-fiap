package com.fiap.hackathon.repositories;

import com.fiap.hackathon.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
