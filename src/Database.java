import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {

    private List<Flight> flightList;
    private HashMap<Integer, Passenger> passengerList;
    private int total;

    Database() {
        this.flightList = new ArrayList<>();
        this.passengerList = new HashMap<>();
        total = 0;
    }

    void addFlight(int capacity) {
        Flight f = new Flight(capacity);
        this.flightList.add(f);
    }

    void addPassenger() {
        Passenger p = new Passenger();
        this.passengerList.put(p.getPassengerID(), p);
        for (Flight f : this.flightList)
            f.addPassenger(p);
    }

    void Reserve(Flight f, int id) {
        if (f.getLeft() <= 0)
            return;
        Passenger p = this.passengerList.get(id);
        if (p == null)
            return;
        f.addReservation(p);
        ++total;
    }

    void Cancel(Flight f, int id) {
        Passenger p = this.passengerList.get(id);
        if (p == null)
            return;
        if (f.getMyPassengers().get(id) == 0)
            return;
        f.removeReservation(p);
        --total;
    }

     List <Flight> My_Flights(int id) {
        List<Flight> list = new ArrayList<>();
        Passenger p = this.passengerList.get(id);
         if (p == null)
             return null;
         for (Flight f : flightList) {
             if (f.getMyPassengers().get(id) == 1)
                 list.add(f);
         }
         return list;
    }

    int Total_Reservations() {
        return this.total;
    }

    void Transfer(Flight f1, Flight f2, int id) {
        Passenger p = this.passengerList.get(id);
        if (p == null)
            return;
        if (f2.getLeft() <= 0 || f1.getMyPassengers().get(p) == 0)
            return;
        f2.addReservation(p);
        f1.removeReservation(p);
    }

}
