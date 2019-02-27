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
        if (f.getLeft() <= 0) {
            System.out.println("No seats left in flight");
            return;
        }
        Passenger p = this.passengerList.get(id);
        if (p == null) {
            System.out.println("not found");
            return;
        }
        System.out.println("Seat reserved");
        f.addReservation(p);
        ++total;
    }

    void Cancel(Flight f, int id) {
        System.out.println("cancel");
        Passenger p = this.passengerList.get(id);
        if (p == null) {
            System.out.println("Passenger " + id + " not found");
            return;
        }
        if (f.getMyPassengers().get(p) == 0) {
            System.out.println("No seat found");
            return;
        }
        f.removeReservation(p);
        --total;
    }

     void My_Flights(int id) {
        System.out.println("My flights");
        List<Flight> list = new ArrayList<>();
        Passenger p = this.passengerList.get(id);
         if (p == null)
             return;
         for (Flight f : flightList) {
             if (f.getMyPassengers().get(p) == 1)
                 list.add(f);
         }
         for (Flight a : list)
             System.out.println(a.getFlightID() + " ");
         System.out.println();
    }

    void Total_Reservations() {
        System.out.println("total res " + this.total);
       // System.out.println();
    }

    void Transfer(Flight f1, Flight f2, int id) {
        System.out.println("transfer");
        Passenger p = this.passengerList.get(id);
        if (p == null)
            return;
        if (f2.getLeft() <= 0 || f1.getMyPassengers().get(p) == 0) {
            System.out.println("Invalid transaction");
            return;
        }
        f2.addReservation(p);
        f1.removeReservation(p);
    }

    List <Flight> getFlightList() {
        return this.flightList;
    }

    HashMap<Integer,Passenger> getPassengerList() {
        return this.passengerList;
    }


}
