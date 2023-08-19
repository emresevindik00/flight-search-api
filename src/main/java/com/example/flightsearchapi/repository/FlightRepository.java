package com.example.flightsearchapi.repository;

import com.example.flightsearchapi.entity.Airport;
import com.example.flightsearchapi.entity.Flight;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("FROM Flight f WHERE (f.goingTo.city = :goingTo and f.leavingFrom.city = :leavingFrom) or (f.goingTo.city = :leavingFrom and f.leavingFrom.city = :goingTo)" +
            " and f.returnDate = :returnDate and f.departureDate = :departureDate")
    List<Flight> twoWayFlights(@Value("goingTo") String goingTo, @Value("leavingFrom") String leavingFrom,
                               @Value("returnDate") Date returnDate, @Value("departureDate") Date departureDate);

    @Query("FROM Flight f WHERE f.goingTo.city = :goingTo and f.leavingFrom.city = :leavingFrom" +
            " and f.departureDate = :departureDate")
    List<Flight> leavingFlights(@Value("goingTo") String goingTo, @Value("leavingFrom") String leavingFrom,
                              @Value("departureDate") Date departureDate);

    @Query("FROM Flight f WHERE (f.goingTo.city = :goingTo and f.leavingFrom.city = :leavingFrom) or " +
            "(f.goingTo.city = :leavingFrom and f.leavingFrom.city = :goingTo) or (f.departureDate = :departureDate or f.departureDate = :returnDate)")
    List<Flight> ornek(@Value("goingTo") String goingTo, @Value("leavingFrom") String leavingFrom,
                       @Value("returnDate") Date returnDate, @Value("departureDate") Date departureDate);
}
