import java.util.ArrayList;
import java.util.List;

public class Flight {

    private int left;
    private static int number = 0;
    private final int capacity, flightID;
    private List<Passenger> myPassengers;

    Flight(int capacity) {
        this.capacity = capacity;
        this.flightID = number;
        ++number;
        setLeft(capacity);
        this.myPassengers = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getFlightID() {
        return flightID;
    }

    public void addReservation(Passenger p) {
        --this.left;
        myPassengers.add(p);
    }

    public void removeReservation(Passenger p){
        ++this.left;
        myPassengers.remove(p);
    }

    public int returnSum() {
        return this.capacity-this.left;
    }

    public List<Passenger> getMyPassengers() {
        return this.myPassengers;
    }
}
