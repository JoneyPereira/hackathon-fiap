package com.fiap.hackathon.services;

import com.fiap.hackathon.dto.ServicesDTO;
import com.fiap.hackathon.entities.Services;
import com.fiap.hackathon.repositories.ServicesRepository;
import com.fiap.hackathon.services.exceptions.DatabaseException;
import com.fiap.hackathon.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
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
public class ServicesService {
    @Autowired
    private ServicesRepository servicesRepository;

    @Transactional(readOnly = true)
    public List<ServicesDTO> findAll() {
        List<Services> listServices = servicesRepository.findAllByOrderByNameAsc();
        return listServices.stream().map(ServicesDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<ServicesDTO> findAllPaged(Pageable pageable){
        Page<Services> list = servicesRepository.findAll(pageable);
        return list.map(ServicesDTO::new);
    }

    @Transactional(readOnly = true)
    public ServicesDTO findById(Long id){
        Optional<Services> obj = servicesRepository.findById(id);
        Services services = obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado, id: " + id));
        return new ServicesDTO(services);
    }

    @Transactional
    public ServicesDTO insert(ServicesDTO dto){
        Services services = new Services();
        copyDtoToEntity(dto, services);
        services = servicesRepository.save(services);
        return new ServicesDTO(services);
    }

    @Transactional
    public ServicesDTO update(Long id, ServicesDTO dto){
        try {
            Services services = servicesRepository.getReferenceById(id);
            copyDtoToEntity(dto, services);
            services = servicesRepository.save(services);
            return new ServicesDTO(services);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
    }

    public void delete(Long id){
        try {
            servicesRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Objeto não encontrado, id: " + id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException("Violação de integridade!");
        }
    }

    private void copyDtoToEntity(ServicesDTO dto, Services room){
    }
}
