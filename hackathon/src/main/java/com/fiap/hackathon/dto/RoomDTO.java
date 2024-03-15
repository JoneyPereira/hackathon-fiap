package com.fiap.hackathon.dto;

import com.fiap.hackathon.entities.Room;
import com.fiap.hackathon.enums.StatusRoom;
import com.fiap.hackathon.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDTO {

    private Long idRoom;
    private Long idLocation;
    private Long idBuilding;
    private Type type;
    private StatusRoom statusRoom;
    private String furniture;


    public RoomDTO(Room entity) {
        idRoom = entity.getIdRoom();
        idLocation = entity.getIdLocation();
        idBuilding = entity.getIdBuilding();
        type = entity.getType();
        statusRoom = entity.getStatusRoom();
        furniture = entity.getFurniture();
    }
}
