package com.fiap.hackathon.resources;


import com.fiap.hackathon.dto.ServicesDTO;
import com.fiap.hackathon.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/services")
public class ServicesController {
    @Autowired
    private ServicesService servicesService;

    @GetMapping
    public ResponseEntity<List<ServicesDTO>> findAll(){
        List<ServicesDTO> listServices = servicesService.findAll();
        return ResponseEntity.ok().body(listServices);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ServicesDTO> findById(@PathVariable Long id){

        ServicesDTO dto = servicesService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ServicesDTO> insert(@RequestBody ServicesDTO dto){

        dto = servicesService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getIdServices()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ServicesDTO> update(@PathVariable Long id, @RequestBody ServicesDTO dto){

        dto = servicesService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id){

        servicesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
