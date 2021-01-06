package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class EditBooking implements Command {
	
	private final int customerId;
	private final int flightId;
	
	public EditBooking(int customerId, int flightId) {
		this.customerId = customerId;
		this.flightId = flightId;
	}
	

	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {		
		
	}

}
