package com.example.flightsearchapi.service.impl;

import com.example.flightsearchapi.entity.Airport;
import com.example.flightsearchapi.entity.Flight;
import com.example.flightsearchapi.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;

@Service
@Slf4j
public class FlightSchedulerService {

    private final FlightService flightService;
    private final RestTemplate restTemplate;
    private final String mockUrl = "http://localhost:8080/api/flights";

    @Autowired
    public FlightSchedulerService(FlightService flightService, RestTemplate restTemplate) {
        this.flightService = flightService;
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void getDataAndSaveFlight() {
        Flight flight = new Flight();

        Date returnDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(returnDate);
        c.add(Calendar.DATE, 1);
        returnDate = c.getTime();

        Airport leavingFrom = new Airport(1,"Ankara");
        Airport goingTo = new Airport(9,"Trabzon");

        flight.setPrice(1500);
        flight.setDepartureDate(new Date());
        flight.setReturnDate(returnDate);
        flight.setLeavingFrom(leavingFrom);
        flight.setGoingTo(goingTo);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin","*");
        responseHeaders.set("Access-Control-Allow-Credentials", "true");
        responseHeaders.set("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        responseHeaders.set("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        responseHeaders.set("Autharization", "Bearer " +
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYSIsImlhdCI6MTY5MjUzNDQ5OCwiZXhwIjoxNjkyNjIwODk4fQ.QCzqmExKjoYkPeF8PFNlyZSQD5UNIdNg9j7KE7aW0N0");

        HttpEntity<Flight> entity = new HttpEntity<>(flight, responseHeaders);

        restTemplate.postForObject(mockUrl, entity, Flight.class);


        log.info("Get Data and Save " + flight.getId());
    }
}
