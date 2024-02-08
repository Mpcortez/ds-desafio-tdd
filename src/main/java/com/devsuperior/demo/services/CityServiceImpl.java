package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.exceptions.DatabaseException;
import com.devsuperior.demo.exceptions.ResourceNotFoundException;
import com.devsuperior.demo.repositories.CityRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CityDTO> findAll() {
        return repository.findAll(Sort.by("name"))
                .stream()
                .map(CityDTO::new)
                .toList();
    }

    @Override
    public CityDTO insert(CityDTO dto) {
        City city = new City();
        city.setName("Recife");

        return new CityDTO(repository.save(city));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) throw new ResourceNotFoundException("City not found.");

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity database error.");
        }
    }
}
