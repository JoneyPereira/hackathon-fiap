package com.fiap.hackathon.resources;


import com.fiap.hackathon.dto.ReservationDTO;
import com.fiap.hackathon.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> findAll(){
        List<ReservationDTO> listServices = reservationService.findAll();
        return ResponseEntity.ok().body(listServices);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReservationDTO> findById(@PathVariable Long id){

        ReservationDTO dto = reservationService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> insert(@RequestBody ReservationDTO dto){

        dto = reservationService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getIdReservation()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ReservationDTO> update(@PathVariable Long id, @RequestBody ReservationDTO dto){

        dto = reservationService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id){

        reservationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
