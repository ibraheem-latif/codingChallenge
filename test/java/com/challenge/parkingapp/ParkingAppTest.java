package test.java.com.challenge.parkingapp;
import main.java.com.challenge.parkingapp.ParkingApp;

public class ParkingAppTest {

    public static void main(String[] args) throws InterruptedException {
        ParkingApp parkingApp = new ParkingApp();

        // Simulate parking cars
        for (int i = 1; i <= 105; i++) {
            String result = parkingApp.parkCar("Car" + i);
            System.out.println("Parking Car" + i + ": " + result);
        }

        // Simulate a car staying for 2 hours
        Thread.sleep(7200000); 

        // Simulate car leaving and check charge
        double charge = parkingApp.carLeaves("Car1");
        System.out.println("Charge for Car1: £" + charge);

        // Test parking a car after one leaves
        String result = parkingApp.parkCar("Car106");
        System.out.println("Parking Car106: " + result);
    }
}
