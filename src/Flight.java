import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Flight {

    private int left;
    private static int number = 0;
    private final int capacity, flightID;
    private HashMap<Passenger, Integer> myPassengers;

    Flight(int capacity) {
        this.capacity = capacity;
        this.flightID = number;
        ++number;
        this.myPassengers = new HashMap<>();
        this.left = capacity;
    }

    void addPassenger(Passenger p) {
        this.myPassengers.put(p,0);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getLeft() {
        return left;
    }

    public int getFlightID() {
        return flightID;
    }

    public void addReservation(Passenger p) {
        if (myPassengers.get(p) == 1)
            return;
        --this.left;
        myPassengers.put(p,1);
    }

    public void removeReservation(Passenger p){
        ++this.left;
        myPassengers.put(p,0);
    }

    public int returnSum() {
        return this.capacity-this.left;
    }

    public HashMap<Passenger, Integer> getMyPassengers() {
        return this.myPassengers;
    }
}
