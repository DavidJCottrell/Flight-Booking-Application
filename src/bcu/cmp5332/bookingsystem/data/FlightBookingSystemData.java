package bcu.cmp5332.bookingsystem.data;



import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightBookingSystemData {
    
    private static final List<DataManager> dataManagers = new ArrayList<>();
    
    // runs only once when the object gets loaded to memory
    static {
        dataManagers.add(new FlightDataManager());
        dataManagers.add(new CustomerDataManager());
        dataManagers.add(new BookingDataManager());
    }
    
    public static FlightBookingSystem load() throws FlightBookingSystemException, IOException {

        FlightBookingSystem fbs = new FlightBookingSystem();
        for (DataManager dm : dataManagers) {
            dm.loadData(fbs);
        }
        for (Flight flight : fbs.getFlights()) {
        	flight.calculateAdditionalCharges(); // All additional charges can be calculated after bookings have been loaded
        }
        return fbs;
    }

    public static void store(FlightBookingSystem fbs) throws IOException {
        for (DataManager dm : dataManagers) {
            dm.storeData(fbs);
        }
    }
    
    public static void storeFlights(FlightBookingSystem fbs) throws IOException {
    	dataManagers.get(0).storeData(fbs);
    }
    
	public static void storeCustomers(FlightBookingSystem fbs) throws IOException {
		dataManagers.get(1).storeData(fbs);
	}

	public static void storeBookings(FlightBookingSystem fbs) throws IOException {
		dataManagers.get(2).storeData(fbs);
	}
    
}
