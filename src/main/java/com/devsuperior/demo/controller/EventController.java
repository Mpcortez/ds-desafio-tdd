package com.devsuperior.demo.controller;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.services.EventService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("events")
public class EventController {

    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @PutMapping(value = "{id}")
    public EventDTO update(@PathVariable Long id, @RequestBody EventDTO dto) {
        return service.update(id, dto);
    }
}
