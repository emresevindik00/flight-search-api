package com.example.flightsearchapi.service;

import com.example.flightsearchapi.entity.Airport;
import com.example.flightsearchapi.entity.Flight;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    List<Flight> findAllFlights();
    Flight saveFlight(Flight flight);
    Optional<Flight> findFlightById(Long id);
    void deleteFlightById(Long id);
    List<Flight> twoWayFlights(String goingTo, String leavingFrom, Date returnDate, Date departureDate);
    Flight updateFlight(Long id, Flight flight);
}
