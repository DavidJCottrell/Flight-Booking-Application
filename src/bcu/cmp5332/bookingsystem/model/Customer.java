package bcu.cmp5332.bookingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private final List<Booking> bookings = new ArrayList<>();
    
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
        return "Customer #" + id + "\n" 
        		+ "Name: " + name + "\n"
        		+ "Phone: " + phone + "\n"
                + "---------------------------" + "\n"
                + "Bookings:\n";
    }
    
    public void addBooking(Booking booking) {
        
    }
}
