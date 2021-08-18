package com.kodigo.group4;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Flight {
    private String flightNumber;
    private String status;
    private String originCountry;
    private String originCity;
    private Date originDate;
    private Date originTime;
    private String departureCountry;
    private String departureCity;
    private Date arrivalDate;
    private Date arrivalTime;
    private boolean landed;

}
