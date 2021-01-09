package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;


/**
 * Hides/removes a customer from the system.
 * 
 * <p>Changes the "hidden" property to true, does not delete the customer from the system.
 * 
 * @see Command
 * 
 */
public class HideCustomer implements  Command {
	
	private final int customerId;
	
	public HideCustomer(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		
		Customer customer = flightBookingSystem.getCustomerByID(this.customerId);
		
		if(!customer.isHidden()) {
			customer.setHidden(true);
			System.out.println("Customer removed.");
		}else {
			System.out.println("Customer has already been removed.");
		}
		
		
		
		
	}

}
