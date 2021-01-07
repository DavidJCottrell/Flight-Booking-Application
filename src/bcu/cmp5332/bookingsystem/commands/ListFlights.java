package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.util.List;

public class ListFlights implements Command {

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        List<Flight> flights = flightBookingSystem.getFlights();
        int i = 0;
        for (Flight flight : flights) {
        	if(!flight.isHidden()) {
        		System.out.println(flight.getDetailsShort());
        		i++;
        	}
        }
        System.out.println("Showing " + i + " out of " + flights.size() + " flights");
    }
}
