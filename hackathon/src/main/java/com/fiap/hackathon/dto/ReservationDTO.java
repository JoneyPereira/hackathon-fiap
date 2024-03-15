package com.fiap.hackathon.dto;

import com.fiap.hackathon.entities.Address;
import com.fiap.hackathon.entities.Client;
import com.fiap.hackathon.entities.Reservation;
import com.fiap.hackathon.entities.Room;
import com.fiap.hackathon.enums.StatusReservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Jacksonized
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO {

    private Long idReservation;

    private Client client;

    private Room room;

    private Integer amount_people;

    private LocalDate entry_date;

    private LocalDate departure_date;

    private StatusReservation statusReservation;


    public ReservationDTO(Reservation entity) {
        idReservation = entity.getIdReservation();
        client = entity.getClient();
        room = entity.getRoom();
        amount_people = entity.getAmount_people();
        entry_date = entity.getEntry_date();
        departure_date = entity.getDeparture_date();
        statusReservation = entity.getStatusReservation();
    }
}
