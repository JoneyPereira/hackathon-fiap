package com.fiap.hackathon.dto;

import com.fiap.hackathon.entities.Room;
import com.fiap.hackathon.entities.Services;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServicesDTO {

    private Long idServices;
    private String description;
    private Double price;


    public ServicesDTO(Services entity) {
        idServices = entity.getIdServices();
        description = entity.getDescription();
        price = entity.getPrice();
    }
}
