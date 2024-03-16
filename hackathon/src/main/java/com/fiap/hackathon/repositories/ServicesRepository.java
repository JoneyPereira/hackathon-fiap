package com.fiap.hackathon.repositories;

import com.fiap.hackathon.entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Long> {
}
