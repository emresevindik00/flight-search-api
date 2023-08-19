package com.example.flightsearchapi.service.impl;

import com.example.flightsearchapi.entity.Airport;
import com.example.flightsearchapi.entity.Flight;
import com.example.flightsearchapi.service.FlightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@Slf4j
public class FlightSchedulerService {

    public FlightService flightService;

    @Autowired
    public FlightSchedulerService(FlightService flightService) {
        this.flightService = flightService;
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
        flightService.saveFlight(flight);
        log.info("Get Data and Save " + flight.getId());
    }
}
