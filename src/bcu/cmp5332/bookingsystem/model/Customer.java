package bcu.cmp5332.bookingsystem.model;

import java.util.ArrayList;
import java.util.List;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private List<Booking> bookings = new ArrayList<>();
    private String email;
    private boolean hidden;
    
    public Customer(int id, String name, String phone, String email, boolean hidden){
    	this.id = id;
    	this.name = name;
    	this.phone = phone;
    	this.email = email;
    	this.hidden = hidden;
    }
    
    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getPhone() { return this.phone; }
    public String getEmail() { return this.email; }
    public List<Booking> getBookings(){ return this.bookings; }
    public boolean isHidden() { return this.hidden; }
    
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public void setHidden(boolean hidden) {
    	this.hidden = hidden;
    }
    
    public String getDetailsShort() {
    	return "* Customer ID - " + this.id + " Name - " + this.name + " Phone - " + this.phone + " Email - " + this.email;
    }
    
    public Booking getBookingByFlight(Flight flight) throws FlightBookingSystemException {
    	for(Booking booking : bookings) {
    		if(booking.getFlight() == flight) return booking;
    	}
    	throw new FlightBookingSystemException("Error: booking for flight not found.");
    }
    
    public String getDetailsLong() {
    	
    	String infoStr = "Customer #" + id + "\n" 
        		+ "Name: " + name + "\n"
        		+ "Phone: " + phone + "\n"
        		+ "Email: " + email + "\n"
                + "---------------------------" + "\n"
                + "Bookings:\n";
    	
    	for(Booking booking : this.bookings) {
    		infoStr = infoStr.concat(
    			"* Booking ID: " + booking.getId() 
	    		+ " \n\t- Made on: " + booking.getBookingDate() 
				+ " \n\t- Flight ID: " + booking.getFlight().getId()
				+ " \n\t- Flight number: " +  booking.getFlight().getFlightNumber()
				+ " \n\t- Flight origin: " + booking.getFlight().getOrigin()
				+ " \n\t- Flight destination: " + booking.getFlight().getDestination()
				+ " \n\t- Flight departure: " + booking.getFlight().getDepartureDate()
				+ " \n\t- Price £" + booking.getPrice()
				+ " \n\t- Cancelation/Re-book price £" + booking.getCancelPrice() + "\n"
			);
    	}
    	
    	if(bookings.size() == 0) infoStr = infoStr.concat("* No bookings.");
    	
    	
        return infoStr;
    }
    
    
    
    public void addBooking(Booking booking) throws FlightBookingSystemException {
    	
    	//Check if booking for flight already exists for customer
    	for(Booking tempBooking : this.bookings) {
        	if(booking.getFlight().getId() == tempBooking.getFlight().getId()) {
        		throw new FlightBookingSystemException("This booking already exists for this customer.");
        	}
    	}
    	
    	this.bookings.add(booking); 
    		
    }
    
    public void cancelBookingForFlight(Flight flight) throws FlightBookingSystemException {
    	int i = 0;
    	for(Booking booking : bookings) {
    		if(booking.getFlight() == flight) {
    			this.bookings.remove(i);
    			return;
    		}
    		i++;
    	}
		throw new FlightBookingSystemException("This booking does not exist for this customer.");
    }
    
}




