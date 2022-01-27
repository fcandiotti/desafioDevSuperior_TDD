package com.devsuperior.Desafiotdd.service;

import com.devsuperior.Desafiotdd.dto.EventDTO;
import com.devsuperior.Desafiotdd.entities.City;
import com.devsuperior.Desafiotdd.entities.Event;
import com.devsuperior.Desafiotdd.repositories.CityRepository;
import com.devsuperior.Desafiotdd.repositories.EventRepository;
import com.devsuperior.Desafiotdd.service.exceptions.ResourceNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class EventService {

    @Autowired
    EventRepository repository;

    @Autowired
    CityRepository cityRepository;

    @Transactional
    public EventDTO update(Long id, EventDTO dto) {
        try {
            Event entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new EventDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundExceptions("Id not found " + id);
        }
    }

        private void copyDtoToEntity(EventDTO dto, Event entity) {
            City city = cityRepository.getOne(dto.getCityId());

            entity.setName(dto.getName());
            entity.setUrl(dto.getUrl());
            entity.setDate(dto.getDate());
            entity.setCity(city);
    }
}

