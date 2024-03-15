package com.fiap.hackathon.entities;

import com.fiap.hackathon.enums.StatusRoom;
import com.fiap.hackathon.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_room")
public class Room implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoom;

    private Long idLocation;

    private Long idBuilding;

    private Type type;

    private StatusRoom statusRoom;

    @Column(columnDefinition = "TEXT")
    private String furniture;
}
