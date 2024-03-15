package com.fiap.hackathon.services;


import com.fiap.hackathon.dto.RoomDTO;
import com.fiap.hackathon.entities.Room;
import com.fiap.hackathon.repositories.RoomRepository;
import com.fiap.hackathon.services.exceptions.DatabaseException;
import com.fiap.hackathon.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Transactional(readOnly = true)
    public List<RoomDTO> findAll() {
        List<Room> listProcuts = roomRepository.findAllByOrderByNameAsc();
        return listProcuts.stream().map(RoomDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<RoomDTO> findAllPaged(Pageable pageable){
        Page<Room> list = roomRepository.findAll(pageable);
        return list.map(RoomDTO::new);
    }

    @Transactional(readOnly = true)
    public RoomDTO findById(Long id){
        Optional<Room> obj = roomRepository.findById(id);
        Room product = obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado, id: " + id));
        return new RoomDTO(product);
    }

    @Transactional
    public RoomDTO insert(RoomDTO dto){
        Room room = new Room();
        copyDtoToEntity(dto, room);
        room = roomRepository.save(room);
        return new RoomDTO(room);
    }

    @Transactional
    public RoomDTO update(Long id, RoomDTO dto){
        try {
            Room room = roomRepository.getReferenceById(id);
            copyDtoToEntity(dto, room);
            room = roomRepository.save(room);
            return new RoomDTO(room);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
    }

    public void delete(Long id){
        try {
            roomRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade!");
        }
    }

    private void copyDtoToEntity(RoomDTO dto, Room room){
        BeanUtils.copyProperties(room, dto);
    }
}
