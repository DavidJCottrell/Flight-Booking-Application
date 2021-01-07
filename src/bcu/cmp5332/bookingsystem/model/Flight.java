package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

public class Flight {
    
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private int capacity;
    private double price;
    private boolean hidden;

    private Set<Customer> passengers;

    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate, int capacity, double price, boolean hidden) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.capacity = capacity;
        this.price = price;
        this.hidden = hidden;
        
        this.passengers = new HashSet<>();
    }
    
    public void removePassenger(Customer customer) {
    	this.passengers.remove(customer);
    }

    public double getPrice() {
    	return this.price;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
    
    public int getCapacity() {
    	return this.capacity;
    }
    
    public boolean isHidden() {
    	return this.hidden;
    }
    
    public void setHidden(boolean hidden) {
    	this.hidden = hidden;
    }
	
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf);
    }

    public String getDetailsLong() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        String infoStr = "Flight #" + id + "\n" 
        		+ "Flight No: " + flightNumber + "\n"
        		+ "Origin: " + origin + "\n"
        		+ "Destination: " + destination + "\n" 
                + "Departure Date: " + departureDate.format(dtf) + "\n"
                + "Price: £" + price + "\n"
                + "Capacity: " + capacity + "\n"
                + "Spaces: " + (capacity-passengers.size()) + "\n"
                + "---------------------------" + "\n"
                + "Passengers:\n";
        
    	if(passengers.size() == 0) {
    		infoStr = infoStr.concat("* No passengers.");
    	}else {
        	for(Customer customer : this.passengers) {
        		infoStr = infoStr.concat(
    	    		"* Id: " + customer.getId() 
    				+ " - " + customer.getName()
    				+ " - " +  customer.getPhone() + "\n"
    			);
        	}
    		infoStr = infoStr.concat(this.passengers.size() + " passenger(s)");
    	}
    	
    	return infoStr;
        
    }
    
    public void addPassenger(Customer passenger) throws FlightBookingSystemException {
    	for(Customer tempCustomer : this.passengers) 
    		if(tempCustomer.getId() == passenger.getId()) 
    			throw new FlightBookingSystemException("This passenger is already booked for this flight.");
    	
    	if(this.passengers.size() >= this.capacity) {
    		throw new FlightBookingSystemException("This flight is at capacity.");	
    	}
    	this.passengers.add(passenger);
    }
}
