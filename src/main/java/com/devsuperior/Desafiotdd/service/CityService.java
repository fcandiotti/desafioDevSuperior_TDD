package com.devsuperior.Desafiotdd.service;

import com.devsuperior.Desafiotdd.dto.CityDTO;
import com.devsuperior.Desafiotdd.entities.City;
import com.devsuperior.Desafiotdd.repositories.CityRepository;
import com.devsuperior.Desafiotdd.service.exceptions.DatabaseException;
import com.devsuperior.Desafiotdd.service.exceptions.ResourceNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        List<City> list = repository.findAll(Sort.by("name"));
        return list.stream().map(CityDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public CityDTO insert(CityDTO dto) {
        City entity = new City();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CityDTO(entity);
    }


    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundExceptions("ID not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }
}
