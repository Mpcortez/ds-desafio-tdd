package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CityService {

    @Transactional(readOnly = true)
    List<CityDTO> findAll();

    @Transactional
    CityDTO insert(CityDTO dto);

    @Transactional(propagation = Propagation.SUPPORTS)
    void delete(Long id);
}
