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
		
		Booking booking = flightBookingSystem.getBookingById(this.bookingId); // Find the booking from the provided ID
		
		Flight oldFlight = booking.getFlight(); //Find the flight this booking currently is made for
		Flight newFlight = flightBookingSystem.getFlightByID(this.flightId); //Find the flight the user wants to change it to
		
		if(newFlight.hasDeparted(flightBookingSystem.getSystemDate())) {
			throw new FlightBookingSystemException("Flight has already departed, please select an available flight");
		}
		
		//Make sure the flights are different
		if(oldFlight.getId() == newFlight.getId()) {
			System.out.println("This booking is already made for that flight.");
		}else {
			
			oldFlight.removePassenger(booking.getCustomer()); //Remove passenger from original flight
			newFlight.addPassenger(booking.getCustomer()); //Add passenger to new flight
			
			double oldCancelPrice = booking.getCancelPrice();
			
			booking.setFlightWithCharge(newFlight);
			
			//Store new booking info to file
	        try {
	        	FlightBookingSystemData.storeBookings(flightBookingSystem);
	        	System.out.println("Changed booking with ID " + booking.getId() + " from flight ID " + oldFlight.getId() + " to flight ID " + newFlight.getId() + ". £" + oldCancelPrice + " was added to the new booking price.");
			} catch (IOException e) {
				booking.setFlight(oldFlight);
				System.out.println("Error storing data to file. Booking not changed");
			}
		}

	}

}
