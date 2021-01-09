package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.util.*;

public class FlightBookingSystem {
    
    private final LocalDate systemDate = LocalDate.parse("2021-03-27");
    
    private final Map<Integer, Customer> customers = new TreeMap<>();
    private final Map<Integer, Flight> flights = new TreeMap<>();
    private final Map<Integer, Booking> bookings = new TreeMap<>();

    public LocalDate getSystemDate() {
        return systemDate;
    }

    //Return all flights stored in the system
    public List<Flight> getFlights() {
        List<Flight> out = new ArrayList<>(flights.values());
        return Collections.unmodifiableList(out);
    }
    
    //Return all customers stored in the system
    public List<Customer> getCustomers() {
        List<Customer> out = new ArrayList<>(customers.values());
        return Collections.unmodifiableList(out);
    }

    //Returns all bookings stored in the system
    public List<Booking> getBookings(){
    	List<Booking> out = new ArrayList<>(bookings.values());
        return Collections.unmodifiableList(out);
    }
    
    //Return a flight with a specific ID
    public Flight getFlightByID(int id) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("There is no flight with that ID.");
        }
        return flights.get(id);
    }

    //Return a customer with a specific ID 
    public Customer getCustomerByID(int id) throws FlightBookingSystemException {
        if (!customers.containsKey(id)) {
            throw new FlightBookingSystemException("There is no customer with that ID.");
        }
        return customers.get(id);
    }
    
    public Booking getBookingById(int id) throws FlightBookingSystemException {
        if (!bookings.containsKey(id)) {
            throw new FlightBookingSystemException("There is no booking with that ID.");
        }
        return bookings.get(id);
    }

    //Add a flight to the list of flights stored in the system
    public void addFlight(Flight flight) throws FlightBookingSystemException {
        if (flights.containsKey(flight.getId())) {
            throw new IllegalArgumentException("Duplicate flight ID.");
        }
        for (Flight existing : flights.values()) {
            if (existing.getFlightNumber().equals(flight.getFlightNumber()) 
                && existing.getDepartureDate().isEqual(flight.getDepartureDate())) {
                throw new FlightBookingSystemException("There is a flight with same " + "number and departure date in the system");
            }
        }
        flights.put(flight.getId(), flight);
    }

    //Add a customer to the list of customers stored in the system
    public void addCustomer(Customer customer) {	
    	if (customers.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Duplicate customer ID.");
        }
    	customers.put(customer.getId(), customer);
    }

    //Add a booking to the list of bookings stored in the system
    public void addBooking(Booking booking) {
    	if (bookings.containsKey(booking.getId())) {
            throw new IllegalArgumentException("Duplicate booking ID.");
        }
    	bookings.put(booking.getId(), booking);
    }

    
    public void removeBooking(Booking booking) {
    	bookings.remove(booking.getId());
    }
    
    
    public void removeCustomer(Customer customer) {
    	customers.remove(customer.getId());
    }
    
    
    public void removeFlight(Flight flight) {
    	flights.remove(flight.getId());
    }
    
    
}
