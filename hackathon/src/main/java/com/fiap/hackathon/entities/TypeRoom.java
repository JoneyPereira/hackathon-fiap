package com.fiap.hackathon.entities;

import com.fiap.hackathon.enums.Bathroom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_type_room")
public class TypeRoom implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeRoom;

    private Long type;

    private String name;

    private Long capacity;

    private Long bed;

    private Bathroom bathroom;

    @Column(columnDefinition = "TEXT")
    private String furniture;

    private Double price;

    private Integer room;
}
