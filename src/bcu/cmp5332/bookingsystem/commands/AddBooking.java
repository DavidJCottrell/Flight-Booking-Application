package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class AddBooking implements Command{

	private final int flightId;
	private final int customerId;
	
	public AddBooking(int customerId, int flightId) {
		this.customerId = customerId;
		this.flightId = flightId;
	}
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		
		Customer customer = flightBookingSystem.getCustomerByID(this.customerId);
		Flight flight = flightBookingSystem.getFlightByID(this.flightId);
		
		Booking booking = new Booking(customer, flight, flightBookingSystem.getSystemDate());
		
		customer.addBooking(booking);
		flight.addPassenger(customer);
		
		System.out.println(customer.getName() + " has been successfully added to flight #" + flight.getFlightNumber());
		
	}
	
	

}
