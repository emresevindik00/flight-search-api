package com.example.flightsearchapi.service.impl;

import com.example.flightsearchapi.entity.Airport;
import com.example.flightsearchapi.entity.Flight;
import com.example.flightsearchapi.repository.FlightRepository;
import com.example.flightsearchapi.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FlightManager implements FlightService{
    private final FlightRepository flightRepository;

    @Autowired
    public FlightManager(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> findAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight saveFlight(Flight flight) {
        if (flightRepository.existsById(flight.getId())) {
            throw new RuntimeException(flight.getId() + "'ye sahip uçuş bulunmaktadır.");
        }
        return flightRepository.save(flight);
    }

    @Override
    public Optional<Flight> findFlightById(Long id) {
        return Optional.ofNullable(flightRepository.findById(id).orElseThrow(
                () -> new RuntimeException(id + "'ye sahip uçuş bulunamadı."))
        );
    }

    @Override
    public void deleteFlightById(Long id) {
        if (!flightRepository.existsById(id)) {
            new RuntimeException(id + "'ye sahip uçuş bulunamadı.");
        }
        flightRepository.deleteById(id);
    }

    @Override
    public List<Flight> twoWayFlights(String goingTo, String leavingFrom, Date returnDate, Date departureDate) {

        if (returnDate == null) {
            return flightRepository.leavingFlights(goingTo, leavingFrom, departureDate);
        }
        return flightRepository.ornek(goingTo, leavingFrom, returnDate, departureDate);
    }

    @Override
    public Flight updateFlight(Long id, Flight flight) {
        Flight existFlight = flightRepository.findById(id).orElse(null);

        if (existFlight != null) {
            existFlight.setPrice(flight.getPrice());
            existFlight.setGoingTo(flight.getGoingTo());
            existFlight.setLeavingFrom(flight.getLeavingFrom());
            existFlight.setDepartureDate(flight.getDepartureDate());
            existFlight.setReturnDate(flight.getReturnDate());
            existFlight.setPrice(flight.getPrice());
        }
        return existFlight;
    }

}
