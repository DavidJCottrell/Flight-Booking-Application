package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    

    public Booking(Customer customer, Flight flight, LocalDate bookingDate) {
        this.customer = customer;
        this.flight = flight;
        this.bookingDate = bookingDate;
    }
    
    public Customer getCustomer() { return this.customer; }
    public Flight getFlight() { return this.flight; }
    public LocalDate getBookingDate() { return this.bookingDate; }
    
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setFlight(Flight flight) { this.flight = flight; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }
    
}
