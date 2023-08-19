package com.example.flightsearchapi.controller;

import com.example.flightsearchapi.entity.Airport;
import com.example.flightsearchapi.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AirportController {
    private AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> findAll() {
        return ResponseEntity.ok(airportService.findAllAirports());
    }

    @PostMapping("/airports")
    public ResponseEntity<Airport> save(@RequestBody Airport airport) {
        return ResponseEntity.ok(airportService.saveAirport(airport));
    }

    @PutMapping("/airports/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport airport) {
        return ResponseEntity.ok(airportService.updateAirport(id, airport));
    }

    @GetMapping("/airports/{id}")
    public ResponseEntity<Optional<Airport>> findAirportById(@PathVariable Long id) {
        return ResponseEntity.ok(airportService.findAirportById(id));
    }

    @DeleteMapping("/airports/{id}")
    public String deleteAirport(@PathVariable Long id) {
        airportService.deleteAirportById(id);
        return "Airport with " + id + " deleted.";
    }
}
