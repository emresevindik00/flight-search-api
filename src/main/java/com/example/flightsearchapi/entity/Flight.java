package com.example.flightsearchapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "leaving_from", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_leaving_id"))
    private Airport leavingFrom;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "going_to", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_going_id"))
    private Airport goingTo;

    @Column(name = "departure_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date departureDate;

    @Column(name = "return_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date returnDate;

    @Column(name = "price")
    private int price;
}
