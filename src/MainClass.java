import java.util.Random;

public class MainClass {

    public static void main(String[] args) {
        Database d = new Database();
        for (int i = 0; i < 5; ++i)
            d.addFlight(100 + i);

        for (int i = 0; i < 500; ++i)
            d.addPassenger();


    }
}
