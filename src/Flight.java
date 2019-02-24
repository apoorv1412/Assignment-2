import java.util.ArrayList;
import java.util.List;

public class Flight {

    private int left;
    private static int number = 0;
    private final int capacity, flightID;
    private List<Integer> passengerList;

    Flight(int capacity) {
        this.capacity = capacity;
        this.flightID = number;
        ++number;
        setLeft(capacity);
        this.passengerList = new ArrayList<>();
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

    public void addReservation(int id) {
        --this.left;
        passengerList.add(id);
    }

    public void removeReservation(int id){
        ++this.left;
        passengerList.remove(id);
    }

    public int returnSum() {
        return this.capacity-this.left;
    }

}
