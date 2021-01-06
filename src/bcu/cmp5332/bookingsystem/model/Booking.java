package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    private int id;

    public Booking(int id, Customer customer, Flight flight, LocalDate bookingDate) {
        this.id = id;
    	this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
    }
    
    public int getId() { return this.id; }
    public Customer getCustomer() { return this.customer; }
    public Flight getFlight() { return this.flight; }
    public LocalDate getBookingDate() { return this.bookingDate; }
    
    public void setId(int id) { this.id = id; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setFlight(Flight flight) { this.flight = flight; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
    
}
