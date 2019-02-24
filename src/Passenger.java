import java.util.ArrayList;
import java.util.List;

public class Passenger {

    private static int number = 0;
    private final int passengerID;
    private List<Flight> myFlights;

    Passenger() {
        passengerID = number;
        ++number;
        myFlights = new ArrayList<>();
    }

    public int getPassengerID() {
        return passengerID;
    }

    public List<Flight> getMyFlights() {
        return myFlights;
    }

    public boolean cancelFlight(Flight f) {
        if (this.myFlights.contains(f)) {
            this.myFlights.remove(f);
            return true;
        }
        return false;
    }

    public void addFlight(Flight f) {
        this.myFlights.add(f);
    }

}
