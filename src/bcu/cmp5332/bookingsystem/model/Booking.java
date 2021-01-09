package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    private double price;
    private double cancelPrice;
    private int id;

    public Booking(int id, Customer customer, Flight flight, LocalDate bookingDate, double price) {
        this.id = id;
    	this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
        this.price = price;
        this.cancelPrice = Math.round((flight.getPrice()*0.25)*100.0)/100.0; //25% of flight price
    }
    
    public int getId() { return this.id; }
    public Customer getCustomer() { return this.customer; }
    public Flight getFlight() { return this.flight; }
    public LocalDate getBookingDate() { return this.bookingDate; }
    
    public double getPrice() { return this.price; }
    public double getCancelPrice() { return this.cancelPrice; }
    
    public void setId(int id) { this.id = id; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    
    public void setFlightWithCharge(Flight newFlight) { 
    	price = Math.round((newFlight.getPrice() + cancelPrice)*100.0)/100.0;
    	cancelPrice = Math.round((newFlight.getPrice()*0.25)*100.0)/100.0;
    	this.flight = newFlight;    	
    }
    
    public void setFlight(Flight newFlight) { 
    	price = Math.round(newFlight.getPrice()*100.0)/100.0;
    	cancelPrice = Math.round((newFlight.getPrice()*0.25)*100.0)/100.0;
    	this.flight = newFlight;   
    }
    

    
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
    
}
