import java.util.ArrayList;
import java.util.List;

public class Passenger {

    private static int number = 0;
    private final int passengerID;

    Passenger() {
        passengerID = number;
        ++number;
    }

    public int getPassengerID() {
        return passengerID;
    }

}
