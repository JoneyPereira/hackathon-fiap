package com.fiap.hackathon.services;

import com.fiap.hackathon.dto.ReservationDTO;
import com.fiap.hackathon.entities.Reservation;
import com.fiap.hackathon.repositories.ReservationRepository;
import com.fiap.hackathon.services.exceptions.DatabaseException;
import com.fiap.hackathon.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional(readOnly = true)
    public List<ReservationDTO> findAll() {
        List<Reservation> listReservation = reservationRepository.findAllByOrderByNameAsc();
        return listReservation.stream().map(ReservationDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ReservationDTO> findAllPaged(Pageable pageable){
        Page<Reservation> list = reservationRepository.findAll(pageable);
        return list.map(ReservationDTO::new);
    }

    @Transactional(readOnly = true)
    public ReservationDTO findById(Long id){
        Optional<Reservation> obj = reservationRepository.findById(id);
        Reservation services = obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado, id: " + id));
        return new ReservationDTO(services);
    }

    @Transactional
    public ReservationDTO insert(ReservationDTO dto){
        Reservation reservation = new Reservation();
        copyDtoToEntity(dto, reservation);
        reservation = reservationRepository.save(reservation);
        return new ReservationDTO(reservation);
    }

    @Transactional
    public ReservationDTO update(Long id, ReservationDTO dto){
        try {
            Reservation reservation = reservationRepository.getReferenceById(id);
            copyDtoToEntity(dto, reservation);
            reservation = reservationRepository.save(reservation);
            return new ReservationDTO(reservation);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
    }

    public void delete(Long id){
        try {
            reservationRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade!");
        }
    }

    private void copyDtoToEntity(ReservationDTO dto, Reservation reservation){
        BeanUtils.copyProperties(reservation, dto);
    }
}
