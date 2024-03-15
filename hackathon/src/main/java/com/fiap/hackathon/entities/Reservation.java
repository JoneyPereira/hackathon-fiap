package com.fiap.hackathon.entities;

import com.fiap.hackathon.enums.StatusReservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_reservation")
public class Reservation implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private Client client;

    private Room room;

    private Integer amount_people;

    private LocalDate entry_date;

    private LocalDate departure_date;

    private StatusReservation statusReservation;
}
