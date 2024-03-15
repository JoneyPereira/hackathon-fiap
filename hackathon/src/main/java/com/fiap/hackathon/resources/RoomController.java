package com.fiap.hackathon.resources;

import com.fiap.hackathon.dto.RoomDTO;
import com.fiap.hackathon.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> findAll(){
        List<RoomDTO> listRooms = roomService.findAll();
        return ResponseEntity.ok().body(listRooms);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RoomDTO> findById(@PathVariable Long id){

        RoomDTO dto = roomService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<RoomDTO> insert(@RequestBody RoomDTO dto){

        dto = roomService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getIdRoom()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RoomDTO> update(@PathVariable Long id, @RequestBody RoomDTO dto){

        dto = roomService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id){

        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
