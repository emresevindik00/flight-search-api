package com.example.flightsearchapi.service;

import com.example.flightsearchapi.entity.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportService {
    List<Airport> findAllAirports();
    Airport saveAirport(Airport airport);
    Optional<Airport> findAirportById(Long id);
    void deleteAirportById(Long id);
    Airport updateAirport(Long id, Airport airport);
}
