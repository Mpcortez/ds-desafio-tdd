package com.devsuperior.demo.controller;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.services.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cities")
public class CityController {

    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping
    public List<CityDTO> findALl() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityDTO insert(@RequestBody CityDTO dto) {
        return service.insert(dto);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
