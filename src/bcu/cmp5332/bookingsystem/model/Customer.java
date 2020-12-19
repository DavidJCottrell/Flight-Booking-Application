package bcu.cmp5332.bookingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private List<Booking> bookings = new ArrayList<>();
    
    public Customer(int id, String name, String phone){
    	this.id = id;
    	this.name = name;
    	this.phone = phone;
    }
    
    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getPhone() { return this.phone; }
    
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getDetailsShort() {
    	return "Customer ID - " + this.id + " Name - " + this.name + " Phone - " + this.phone;
    }
    
    public String getDetailsLong() {
    	
    	String infoStr = "Customer #" + id + "\n" 
        		+ "Name: " + name + "\n"
        		+ "Phone: " + phone + "\n"
                + "---------------------------" + "\n"
                + "Bookings:\n";
    	
    	for(Booking booking : this.bookings) {
    		infoStr = infoStr.concat(
	    		"* Booking date: " + booking.getBookingDate() 
				+ " for Flight #" + booking.getFlight().getId()
				+ " - " +  booking.getFlight().getFlightNumber()
				+ " - " + booking.getFlight().getOrigin()
				+ " to " + booking.getFlight().getDestination()
				+ " on " + booking.getFlight().getDepartureDate()
			);
    	}
    	
    	if(bookings.size() == 0) infoStr = infoStr.concat("* No bookings.");
    	
    	
        return infoStr;
    }
    
    
    
    public void addBooking(Booking booking) {
    	this.bookings.add(booking);
    }
    
    public void removeBooking(int id) {
    	int i = 0;
    	for(Booking booking : bookings) {
    		if(booking.getCustomer().getId() == id) break;
    		i++;
    	}
		this.bookings.remove(i);
    }
    
}




