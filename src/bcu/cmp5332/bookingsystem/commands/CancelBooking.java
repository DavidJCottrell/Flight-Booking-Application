package bcu.cmp5332.bookingsystem.commands;


import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
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
		
		//Remove booking from customer
		Customer customer = flightBookingSystem.getCustomerByID(this.customerId);
		customer.removeBooking(this.customerId);
		
		
		//Remove booking from flight
		Flight flight = flightBookingSystem.getFlightByID(this.flightId);
		flight.removePassenger(customer);
		
		
	}
}
