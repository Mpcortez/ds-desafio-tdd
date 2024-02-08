package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.exceptions.ResourceNotFoundException;
import com.devsuperior.demo.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public EventDTO update(Long id, EventDTO dto) {
        try {
            Event event = repository.getReferenceById(id);

            event.setName(dto.getName());
            event.setDate(dto.getDate());
            event.setUrl(dto.getUrl());
            event.setCity(new City(dto.getCityId(), null));

            return new EventDTO(repository.save(event));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Event not found.");
        }
    }
}
