package bcu.cmp5332.bookingsystem.commands;

import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ListCustomers implements Command{

	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		List<Customer> customers = flightBookingSystem.getCustomers();
        int i = 0;
		for (Customer customer : customers) {
        	if(!customer.isHidden()) {
        		System.out.println(customer.getDetailsShort());
        		i++;
        	}
        }
        System.out.println("Showing " + i + " out of " + customers.size() + " customers");
		
	}

}
