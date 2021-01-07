package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class HideFlight implements  Command {
	
	private final int flightId;
	
	public HideFlight(int flightId) {
		this.flightId = flightId;
	}

	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		
		Flight flight = flightBookingSystem.getFlightByID(this.flightId);
		
		if(!flight.isHidden()) {
			flight.setHidden(true);
			System.out.println("Flight removed.");
		}else {
			System.out.println("Flight has already been removed.");
		}
	}

}
