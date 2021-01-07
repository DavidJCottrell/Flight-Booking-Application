package bcu.cmp5332.bookingsystem.commands;

import java.io.IOException;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class EditBooking implements Command {
	
	private final int bookingId;
	private final int flightId;
	
	public EditBooking(int bookingId, int flightId) {
		this.bookingId = bookingId;
		this.flightId = flightId;
	}
	

	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {		
		
		Booking booking = flightBookingSystem.getBookingById(this.bookingId);
		
		Flight oldFlight = booking.getFlight();		
		Flight newFlight = flightBookingSystem.getFlightByID(this.flightId);
		
		oldFlight.removePassenger(booking.getCustomer());
		newFlight.addPassenger(booking.getCustomer());
		
		booking.setFlight(newFlight);
		
		
        try {
        	FlightBookingSystemData.storeBookings(flightBookingSystem);
        	System.out.println("Changed booking with ID " + booking.getId() + " from flight ID " + oldFlight.getId() + " to flight ID " + newFlight.getId());
		} catch (IOException e) {
			booking.setFlight(oldFlight);
			System.out.println("Error storing data to file. Booking not changed");
		}
        
	}

}
