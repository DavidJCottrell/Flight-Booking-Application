package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class CancelBooking implements Command{

	private final int customerId;
	private final int flightId;
	
	public CancelBooking(int customerId, int flightId) {
		this.customerId = customerId;
		this.flightId = flightId;
	}
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		
		//Find relevant customer, flight and booking objects
		Customer customer = flightBookingSystem.getCustomerByID(this.customerId);
		Flight flight = flightBookingSystem.getFlightByID(this.flightId);
		Booking booking = customer.getBookingByFlight(flight);
		
		//Remove booking from customer
		customer.cancelBookingForFlight(flight);
		
		//Remove booking from flight
		flight.removePassenger(customer);
		
		//Remove booking from system
		flightBookingSystem.removeBooking(booking);
		
        try {
			
        	FlightBookingSystemData.storeBookings(flightBookingSystem);
			System.out.println("Booking has been successfully cancelled");
			
		} catch (IOException e) {
			
			customer.addBooking(booking);
			flight.addPassenger(customer);
			flightBookingSystem.addBooking(booking);
			System.out.println("Couldn't store data");
			
		}
	}
}
