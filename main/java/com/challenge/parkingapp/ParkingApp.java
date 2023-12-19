package main.java.com.parkingapp;
import java.util.HashMap;
import java.util.Map;

public class ParkingApp {
    private static final int MAX_CAPACITY = 100;
    private final Map<String, Long> parkedCars;
    private int currentCapacity;

// HashMap for Parking Data: Efficient for lookups (to check if a car is already parked) and
// updates (when a car enters or leaves 
    public ParkingApp() {
        parkedCars = new HashMap<>();
        currentCapacity = MAX_CAPACITY;
    }

    // Synchronization: Handles concurrent access, ensuring data integrity.
    public synchronized String parkCar(String licensePlate) {
        if (currentCapacity > 0) {
            parkedCars.put(licensePlate, System.currentTimeMillis());
            currentCapacity--;
            return "Car parked successfully";
        } else {
            return "Parking full";
        }
    }

    public synchronized double carLeaves(String licensePlate) {
        Long entryTime = parkedCars.remove(licensePlate);
        if (entryTime == null) {
            return 0.0; // Car not found
        }
        currentCapacity++;
        return calculateCharge(entryTime);
    }

    // Simple Charge Calculation: Based on the time difference and a fixed rate.
    private double calculateCharge(Long entryTime) {
        long parkedTime = System.currentTimeMillis() - entryTime;
        return (parkedTime / (1000.0 * 60 * 60)) * 2; // £2 per hour
    }

    // Additional methods can be added for reporting, management, etc.
}
// ----------------Additional notes----------------

// This code is a basic representation. In a real-world application, you would need error handling, 
// validation (like valid license plates), and possibly a database for persistence.

// Time is calculated based on the system's current time, which is simple but not always 
// accurate (e.g., in case of system time changes). In a production scenario, consider using a more 
// reliable time source.

// The synchronized keyword is used for simplicity. For higher concurrency, more sophisticated 
// concurrency controls like ReentrantReadWriteLock could be used.