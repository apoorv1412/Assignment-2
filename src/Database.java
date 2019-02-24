import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Flight> flightList;
    private List<Passenger> passengerList;
    private int total;

    Database() {
        this.flightList = new ArrayList<>();
        this.passengerList = new ArrayList<>();
        total = 0;
    }

    void addFlight(int capacity) {
        Flight f = new Flight(capacity);
        this.flightList.add(f);
    }

    void addPassenger() {
        Passenger p = new Passenger();
        this.passengerList.add(p);
    }

    void Reserve(Flight f, int id) {
        if (f.getLeft() <= 0)
            return;
        boolean found = false;
        Passenger p = null;
        for (Passenger a : passengerList) {
            if (a.getPassengerID() == id) {
                found = true;
                p = a;
                break;
            }
        }
        if (found) {
            f.addReservation(p);
            p.addFlight(f);
            ++total;
        }
    }

    void Cancel(Flight f, int id) {
        Passenger p = null;
        List <Passenger> list = f.getMyPassengers();
        boolean found = false;
        for (Passenger a : list) {
            if (a.getPassengerID() == id) {
                p = a;
                found = true;
                break;
            }
        }
        if (!found)
            return;
        p.cancelFlight(f);
        f.removeReservation(p);
        --total;
    }

     List <Flight> My_Flights(int id) {
        Passenger p = null;
        boolean found = false;
        for (Passenger a : passengerList) {
            if (a.getPassengerID() == id) {
                p = a;
                found = true;
                break;
            }
        }
        if (!found)
            return null;
        return p.getMyFlights();
    }

    int Total_Reservations() {
        return this.total;
    }

    void Transfer(Flight f1, Flight f2, int id) {
        Passenger p = null;
        List <Passenger> list = f1.getMyPassengers();
        boolean found = false;
        for (Passenger a : list) {
            if (a.getPassengerID() == id) {
                p = a;
                found = true;
                break;
            }
        }
        if (f2.getLeft() <= 0 || !found)
            return;
        f2.addReservation(p);
        f1.removeReservation(p);
        p.cancelFlight(f1);
        p.addFlight(f2);
    }

}
