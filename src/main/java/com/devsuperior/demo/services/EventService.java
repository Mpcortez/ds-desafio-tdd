package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import org.springframework.transaction.annotation.Transactional;

public interface EventService {

    @Transactional
    EventDTO update(Long id, EventDTO dto);
}

