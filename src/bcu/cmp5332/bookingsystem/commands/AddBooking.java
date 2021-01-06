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
		
        int maxId = 0;
        if (flightBookingSystem.getBookings().size() > 0) {
            int lastIndex = flightBookingSystem.getBookings().size() - 1;
            maxId = flightBookingSystem.getBookings().get(lastIndex).getId();
        }
		
		Booking booking = new Booking(++maxId, customer, flight, flightBookingSystem.getSystemDate());
		
		customer.addBooking(booking);
		flight.addPassenger(customer);
		flightBookingSystem.addBooking(booking);
		
		System.out.println(customer.getName() + " has been successfully added to flight #" + flight.getFlightNumber());
		
		
	}

}
