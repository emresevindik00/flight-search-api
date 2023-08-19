package com.example.flightsearchapi.controller;

import com.example.flightsearchapi.entity.Flight;
import com.example.flightsearchapi.service.FlightService;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class FlightController {
    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> findAll() {
        return ResponseEntity.ok(flightService.findAllFlights());
    }

    @PostMapping("/flights")
    public ResponseEntity<Flight> save(@RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.saveFlight(flight));
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Optional<Flight>> findFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.findFlightById(id));
    }

    @PutMapping("/flights/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.updateFlight(id, flight));
    }

    @DeleteMapping("/flights/{id}")
    public String deleteFlight(@PathVariable Long id) {
        flightService.deleteFlightById(id);
        return "Flight with " + id + " deleted.";
    }


    @GetMapping("/search")
    public List<Flight> search
            (@RequestParam String goingTo, @RequestParam String leavingFrom,
             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam Date departureDate,
             @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam Date returnDate) {
        return flightService.twoWayFlights(goingTo, leavingFrom, returnDate, departureDate);
    }
}
