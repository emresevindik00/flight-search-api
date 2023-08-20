package com.example.flightsearchapi.service.impl;

import com.example.flightsearchapi.entity.Airport;
import com.example.flightsearchapi.repository.AirportRepository;
import com.example.flightsearchapi.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportManager implements AirportService {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportManager(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }
    @Override
    public List<Airport> findAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Airport saveAirport(Airport airport) {
        if (airportRepository.existsById(airport.getId())) {
            new RuntimeException("Böyle bir havalimanı mevcut!");
        }
        return airportRepository.save(airport);
    }

    @Override
    public Optional<Airport> findAirportById(Long id) {
        return Optional.ofNullable(airportRepository.findById(id).orElseThrow(
                () -> new RuntimeException(id + "'ye sahip havalimanı bulunamadı."))
        );
    }

    @Override
    public void deleteAirportById(Long id) {
        if (!airportRepository.existsById(id)) {
            new RuntimeException(id + "'ye sahip havalimanı bulunamadı.");
        }

        airportRepository.deleteById(id);
    }

    @Override
    public Airport updateAirport(Long id, Airport airport) {
        Airport existAirport = airportRepository.findById(id).orElse(null);

        if (existAirport != null) {
            existAirport.setCity(airport.getCity());
        }
        return existAirport;
    }
}
