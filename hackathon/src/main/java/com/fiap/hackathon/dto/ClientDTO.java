package com.fiap.hackathon.dto;

import com.fiap.hackathon.entities.Address;
import com.fiap.hackathon.entities.Client;
import com.fiap.hackathon.entities.Services;
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
public class ClientDTO {

    private Long idClient;

    private String country;

    private String cpf;

    private String passport;

    private String name;

    private LocalDate dateOfBirth;

    private Address address;

    private String telefone;

    private String email;


    public ClientDTO(Client entity) {
        idClient = entity.getIdClient();
        country = entity.getCountry();
        cpf = entity.getCpf();
        passport = entity.getPassport();
        name = entity.getName();
        dateOfBirth = entity.getDateOfBirth();
        address = entity.getAddress();
        telefone = entity.getTelefone();
        email = entity.getEmail();
    }
}
