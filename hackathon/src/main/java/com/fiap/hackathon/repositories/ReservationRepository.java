package com.fiap.hackathon.repositories;

import com.fiap.hackathon.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
