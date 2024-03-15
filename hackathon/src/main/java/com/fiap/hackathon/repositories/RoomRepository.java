package com.fiap.hackathon.repositories;


import com.fiap.hackathon.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByOrderByNameAsc();
}
